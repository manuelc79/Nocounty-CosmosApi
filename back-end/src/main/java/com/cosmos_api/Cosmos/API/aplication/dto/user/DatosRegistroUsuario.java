package com.cosmos_api.Cosmos.API.aplication.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank(message = "El email no debe estar en blanco")
        @Email
        String email,
        @NotBlank
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
//        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "La contraseña debe contener" +
//                " al menos un número, una letra mayúscula y un caracter especial")
        String password,
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
