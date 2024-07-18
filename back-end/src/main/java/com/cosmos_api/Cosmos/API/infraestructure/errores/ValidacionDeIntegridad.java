package com.cosmos_api.Cosmos.API.infraestructure.errores;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
