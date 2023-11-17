package com.situbackend.rest.modelo_rest;

import java.util.UUID;
import java.util.Date;

import com.situbackend.modelo.Tarjeta;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class TarjetaWS  {

    private String codigo;
    
    private Double saldo;

    private Double montoTotal;

    public Tarjeta cargarTarjeta(Tarjeta tarjeta)
    {
        if(tarjeta==null) tarjeta = new Tarjeta();

        tarjeta.setCodigo(codigo);
        tarjeta.setSaldo(saldo);
        tarjeta.setMontoTotal(montoTotal);
        tarjeta.setExternal_id(UUID.randomUUID().toString());
        tarjeta.setUpdateAt(new Date());

        return tarjeta;
    }
}
