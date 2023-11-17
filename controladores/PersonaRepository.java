package com.situbackend.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.situbackend.modelo.Persona;


/**
 * creamos CuentaRepository para manejar Cuenta, y IDs enteros
 * @param external_id
 * @return 
 */ 
public interface PersonaRepository extends CrudRepository<Persona, Integer>{
    @Query("SELECT p from Persona p WHERE p.external_id = ?1")
    Persona findByExternal_id(String external_id);
}
