package com.cosmos_api.Cosmos.API.aplication.dto.web;

import com.cosmos_api.Cosmos.API.domain.entities.template.Variants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistraWeb(
        @NotBlank(message = "EL nombre no debe estar en blanco")
        String name,
        String image,
        Variants variant,
        @NotNull
        Long usuarioId,
        Long grupoId

) {

}
