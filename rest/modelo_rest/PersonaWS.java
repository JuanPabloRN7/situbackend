package com.situbackend.rest.modelo_rest;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.situbackend.modelo.Persona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonaWS {
    
    private String nombre; 

    private String direccion;

    private String cedula;

    private String external_id;

    private CuentaWS cuenta;    

    @Pattern(regexp = "^(Administrador|Cliente)$", message = "El rol solo puede ser 'Administrador' o 'Cliente'")
    @Size(min=2,max=75)
    private String nombreRol;

    public Persona cargarPersona(Persona persona)
    {
        if(persona==null) persona = new Persona();

        persona.setNombre(nombre);
        persona.setCedula(cedula);
        persona.setDireccion(direccion);
        persona.setExternal_id(UUID.randomUUID().toString());
        persona.setUpdateAt(new Date());

        return persona;
    }
}
