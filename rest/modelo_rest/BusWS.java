package com.situbackend.rest.modelo_rest;


import java.util.Date;
import java.util.UUID;

import com.situbackend.modelo.Bus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusWS {

    private String matricula;

    private String anio;

    private String ruta;

    public Bus cargarBus(Bus bus)
    {
        if(bus==null) bus = new Bus();

        bus.setAnio(anio);
        bus.setRuta(ruta);
        bus.setMatricula(matricula);
        bus.setExternal_id(UUID.randomUUID().toString());
        bus.setUpdateAt(new Date());

        return bus;
    }

}
