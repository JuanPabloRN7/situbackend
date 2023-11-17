package com.situbackend.controladores.utiles;

import org.springframework.security.crypto.bcrypt.BCrypt;//implementa el hash de contrasenas

public class Utilidades 
{
    public static String clave(String clave)
    {
        // el uso de hash es con el fin de mantener la seguridad y confidencialidad a las contrasenas privadas
        // para codificar una contrasena por primera vez hay que llamar a hashpw con una gensalt aleatoria
        return BCrypt.hashpw(clave, BCrypt.gensalt());// gensalt() toma un parametro opcional que determina la complejidad de hashing
    }

    //para verificar si una contrasena de texto sin formato coincide con unaa que se ha cifrado previamente, hay que usar el metodo checkpw
    public static Boolean verificar(String clave, String hash)
    {
        return BCrypt.checkpw(clave, hash);
    }
}