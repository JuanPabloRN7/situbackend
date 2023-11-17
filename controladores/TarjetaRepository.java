package com.situbackend.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.situbackend.modelo.Tarjeta;


/**
 * creamos CuentaRepository para manejar Cuenta, y IDs enteros
 * @param external_id
 * @return 
 */ 
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer>
{
    @Query("Select t from Tarjeta t where t.external_id = ?1")
    Tarjeta findByExternal_id(String external_id);
}
