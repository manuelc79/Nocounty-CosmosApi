package com.cosmos_api.Cosmos.API.aplication.dto.web;

public record DtoRespuestaWeb(
        Long id,
        String name,
        String image,
        String slug,
        String variant,
        Long grupoId
) {
}
