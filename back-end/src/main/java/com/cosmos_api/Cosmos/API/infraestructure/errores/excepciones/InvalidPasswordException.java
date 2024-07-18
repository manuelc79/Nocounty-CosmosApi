package com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensaje) {
        super(mensaje);
    }
}
