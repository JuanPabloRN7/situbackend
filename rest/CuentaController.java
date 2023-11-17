package com.situbackend.rest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;//
import java.util.ArrayList;
import java.util.UUID;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.situbackend.controladores.CuentaRepository;
import com.situbackend.controladores.utiles.Utilidades;
import com.situbackend.modelo.Cuenta;
import com.situbackend.rest.modelo_rest.CuentaWS;
import com.situbackend.rest.respuesta.RespuestaLista;

import org.springframework.web.bind.annotation.PathVariable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController /** es un controller especial en RESTful y equivale a la suma de @Controller y @ResponseBody */
@RequestMapping(value = "/api/v1")/**  se usa para asignar solicitudes web a los metodos de spring controller/ con esto mapeamos, aca vamos a generar nuestra URL, el end point */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD })
public class CuentaController {

    private static final String SECRET = "miClaveSecreta1";
    private final long EXPIRATION_TIME = 3600000; 

    @Autowired /** nos proporciona control a la hora de querer inyectar nuestras dependencias o instancias que se almacenan en el contexto spring */
    private CuentaRepository cuentaRepository;

     @PostMapping("/login")
    public ResponseEntity inicioSesion(@Valid @RequestBody CuentaWS cuentaWs)
    {
        HashMap mapa = new HashMap<>();
        Cuenta cuenta = cuentaRepository.findByUsuario(cuentaWs.getUsuario());
        if(cuenta != null && Utilidades.verificar(cuentaWs.getClave(), cuenta.getClave()))
        {
            mapa.put("token",token(cuenta));
            mapa.put("external", cuenta.getExternal_id());
            mapa.put("usuario", cuenta.getUsuario());
            return RespuestaLista.respuesta(mapa, "OK");
        }
        else
        {
            mapa.put("evento","Cuenta no encontrada");
            return RespuestaLista.respuesta(mapa, "Cuenta no encontrada");
        }
  
    }

    @PostMapping("/cuentas")
        public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        if (cuentaRepository.findByUsuario(cuenta.getUsuario()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        cuenta.setExternal_id(UUID.randomUUID().toString());
        cuenta.setCreateAt(new Date());
        Cuenta cuentaCreada = cuentaRepository.save(cuenta);
        return new ResponseEntity<>(cuentaCreada, HttpStatus.CREATED);
    }


    private String token(Cuenta cuenta)
    {
        String secretKey = SECRET;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(cuenta.getPersona().getRol().getNombreRol());
        String token = Jwts.builder().setId(cuenta.getExternal_id()).setSubject(cuenta.getUsuario()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        return "Bearer " + token;

    }
    
}
