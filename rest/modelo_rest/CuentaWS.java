package com.situbackend.rest.modelo_rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.situbackend.modelo.Cuenta;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter

public class CuentaWS {

    private String usuario;

    @NotBlank(message = "La clave es requerida")
    @NotNull(message = "La clave no puede ser nula")
    private String clave;

    public Cuenta cargarCuenta(Cuenta cuenta)
    {
        if(cuenta==null) cuenta = new Cuenta();

        cuenta.setClave(clave);
        cuenta.setUsuario(usuario);
        cuenta.setUpdateAt(new Date());
        cuenta.setExternal_id(UUID.randomUUID().toString());

        return cuenta;
    }
}
