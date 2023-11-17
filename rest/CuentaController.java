package com.situbackend.rest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;//
import java.util.ArrayList;
import java.util.UUID;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController /** es un controller especial en RESTful y equivale a la suma de @Controller y @ResponseBody */
@RequestMapping(value = "/api/v1")/**  se usa para asignar solicitudes web a los metodos de spring controller/ con esto mapeamos, aca vamos a generar nuestra URL, el end point */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD })
public class CuentaController {

    private static final String SECRET = "miClaveSecreta1";
    private final long EXPIRATION_TIME = 3600000; 
    
}
