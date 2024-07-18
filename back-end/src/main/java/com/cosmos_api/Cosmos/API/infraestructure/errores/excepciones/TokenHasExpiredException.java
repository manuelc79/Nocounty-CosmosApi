package com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones;

public class TokenHasExpiredException extends RuntimeException{
    public TokenHasExpiredException(String mensaje) {
        super(mensaje);
    }
}
