package com.situbackend.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.situbackend.modelo.Cuenta;


/**
 * creamos CuentaRepository para manejar Cuenta, y IDs enteros
 * @param external_id
 * @return 
 */ 
public interface CuentaRepository extends CrudRepository<Cuenta, Integer>
{
    @Query("Select c from Cuenta c where c.usuario = ?1")
    Cuenta findByUsuario(String usuario);

    @Query("Select c from Cuenta c where c.external_id = ?1")
    Cuenta findByExternal_id(String external_id);
}