package com.situbackend.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.situbackend.modelo.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {
    @Query("Select b from Bus b where b.external_id = ?1")
    Bus findByExternal_id(String external_id);
}
