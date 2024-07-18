package com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException (String mensaje) {
        super(mensaje);
    }
}
