package com.situbackend.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.situbackend.modelo.Rol;


/**
 * creamos RolRepository donde se buscar por nombrerol el Rol
 * @param external_id
 * @return 
 */
public interface RolRepository extends CrudRepository<Rol, Integer> 
{
    @Query("Select r from Rol r where r.nombreRol = ?1")
    Rol findByTipoRol(String nombreRol);   
}