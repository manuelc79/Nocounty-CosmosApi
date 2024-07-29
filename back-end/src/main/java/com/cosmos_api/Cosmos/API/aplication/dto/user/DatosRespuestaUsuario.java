package com.cosmos_api.Cosmos.API.aplication.dto.user;

public record DatosRespuestaUsuario(
        Long id,
        String mail,
        String name,
        String lastName,
        String phoneNumber,
        String userName,
        Boolean active ) {
}
