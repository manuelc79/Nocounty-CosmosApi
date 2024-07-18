package com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException (String message) {
        super(message);
    }
}
