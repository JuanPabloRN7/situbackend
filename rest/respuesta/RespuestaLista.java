package com.situbackend.rest.respuesta;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class RespuestaLista {
    /**
     * Este método se utiliza para generar respuestas HTTP que contienen listas de objetos.
     * @param datos
     * @param msg
     * @return ResponseEntity
     */
    public static ResponseEntity respuestaLista(Object datos){
        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.OK.toString());
        rm.setMsg("OK");
        rm.setData(datos);
        return new ResponseEntity<RespuestaModelo>(rm,HttpStatus.OK);
    }

    /**
     *  Este método se utiliza para generar respuestas HTTP que contienen objetos únicos.
     * @param datos
     * @param msg
     * @return ResponseEntity
     */
    public static ResponseEntity respuesta(Object datos, String msg) {
        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.OK.toString());
        rm.setMsg(msg);
        rm.setData(datos);
        return new ResponseEntity<RespuestaModelo>(rm, HttpStatus.OK);
    }
}