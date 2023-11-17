package com.situbackend.rest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.situbackend.controladores.PersonaRepository;
import com.situbackend.controladores.RolRepository;
import com.situbackend.controladores.utiles.Utilidades;
import com.situbackend.modelo.Cuenta;
import com.situbackend.modelo.Persona;
import com.situbackend.modelo.Rol;
import com.situbackend.rest.modelo_rest.PersonaWS;
import com.situbackend.rest.respuesta.RespuestaLista;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;


@RestController /** Indica que esta clase es un controlador REST */ 
@RequestMapping(value = "/api/v1") /**  Especifica la raíz de la URL para todas las solicitudes que maneja este controlador */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD })
public class PersonaController {
    @Autowired /**  Anotación que permite inyectar una dependencia de manera automática */
    private PersonaRepository personaRepository; /** Variable que almacena una instancia de PersonaRepository */
    
    @Autowired // Anotación que permite inyectar una dependencia de manera automática
    private RolRepository rolRepository; /** Variable que almacena una instancia de RolRepository */ 
    
    // Aquí se definen los métodos del controlador que manejan las solicitudes HTTP
    @PostMapping("/personas/guardar")
    public ResponseEntity guardar(@Valid @RequestBody PersonaWS personaWs)
    {
        HashMap mapa = new HashMap<>();
        
        Persona persona = personaWs.cargarPersona(null);
        try{

        Rol rol = rolRepository.findByTipoRol(personaWs.getNombreRol().toLowerCase());
    
        if(rol != null)
        {
            persona.setCreateAt(new Date());
            persona.setRol(rol);
            if(personaWs.getCuenta() != null)
            {
                Cuenta cuenta = personaWs.getCuenta().cargarCuenta(null);
                cuenta.setClave(Utilidades.clave(personaWs.getCuenta().getClave()));
                cuenta.setPersona(persona);
                cuenta.setCreateAt(new Date());
                cuenta.setExternal_id(UUID.randomUUID().toString());
                persona.setCuenta(cuenta);
            }
            mapa.put("evento", "Se ha registrado correctamente");
            personaRepository.save(persona);
            
            return RespuestaLista.respuesta(mapa, "OK");
        }
       
    }catch(DataIntegrityViolationException ex) {
            /** aquí manejas la excepción de violación de restricción de unicidad */
            if (ex.getMessage().contains("UK_r5vsms84ih2viwd6tatk9o5pq")) {
                /** aquí devuelves una respuesta personalizada en el cuerpo de la respuesta */
                String mensaje = "El usuario " + personaWs.getCuenta().getUsuario();
                mapa.put("evento", mensaje);
                return RespuestaLista.respuesta(mapa, "no se pudo registrar");
            } else {
                /**
                 * aquí manejas otras excepciones
                 */
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        mapa.put("evento", "no se pudo procesar la solicitud");
        return RespuestaLista.respuesta(mapa, "OK");
        
    }
}
