package com.cosmos_api.Cosmos.API.aplication.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatoRegistroDetalleusuario(
        @NotBlank(message = "El nombre no debe estar vacío")
        String name,
        @NotBlank(message = "El apellido no debe estar vacío")
        String lastName,
        @NotBlank(message = "El teléfono no debe estar vacío")
        String phoneNumber,
        @NotBlank(message = "El nombre de usuario no debe estas vacío")
        String userName,
        Boolean active
) {
}
