package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.domain.services.UserService;
import com.cosmos_api.Cosmos.API.aplication.security.SecurityFilter;
import com.cosmos_api.Cosmos.API.aplication.dto.usuario.DatosRegistroUsuario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"https://cosmos-seven-delta.vercel.app/", "http://localhost:3000", "https://cosmosapi.up.railway.app/swagger-ui"})
public class UsuarioController {

    @Autowired
    private UserService userService;

    @Autowired
    SecurityFilter securityFilter;

    @PostMapping
    @Transactional

    @Operation(summary = "Registra un nuevo usuario en la base de datos")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
            try {
                userService.registrarUsuario(datosRegistroUsuario);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Usuario creado Correctamente");
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("El correo electrónico ya está en uso");
            }
    }


}
