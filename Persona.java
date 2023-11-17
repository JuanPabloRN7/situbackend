package com.situbackend.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity(name = "persona")

public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String nombre; 

    @Column(length = 50)
    private String direccion;

    @Column(length = 50)
    private String cedula;

    @Column(length = 36)
    private String external_id;

    @CreatedDate
    @Column(name = "create_at", updatable = false, columnDefinition = "datetime default now()")
    private Date createAt;
    @LastModifiedDate
    @Column(name = "update_at", columnDefinition = "datetime default now()")
    private Date updateAt;

    /*  
     *Muchas personas tienen un ROL */
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "id_rol")
    private Rol rol;

    /*Una persona tiene una cuenta */
    @OneToOne (mappedBy = "persona", cascade = CascadeType.ALL)
    private Cuenta cuenta;

    /*Una persona tiene una tarjeta de bus */
    @OneToOne (mappedBy = "persona", cascade = CascadeType.ALL)
    private Tarjeta tarjeta;


}
