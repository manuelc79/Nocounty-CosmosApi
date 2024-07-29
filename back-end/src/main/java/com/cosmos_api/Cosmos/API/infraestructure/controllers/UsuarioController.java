package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.token.DatosJwtToken;
import com.cosmos_api.Cosmos.API.aplication.dto.user.DtoBuscarUsuario;
import com.cosmos_api.Cosmos.API.domain.services.TokenService;
import com.cosmos_api.Cosmos.API.domain.services.UserService;
import com.cosmos_api.Cosmos.API.aplication.security.SecurityFilter;
import com.cosmos_api.Cosmos.API.aplication.dto.user.DatosRegistroUsuario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    SecurityFilter securityFilter;

    @PostMapping
    @Transactional

    @Operation(summary = "Registra un nuevo usuario en la base de datos")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
            try {
                var usuario = userService.registrarUsuario(datosRegistroUsuario);
                var token = tokenService.generarToken(usuario);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new DatosJwtToken(token, usuario.getId()));
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("El correo electrónico ya está en uso");
            }
    }

    @PostMapping("/find")
    @Operation(summary = "Recibe el Id del usuario, lo busca en la base de datos y devuelve los datos del usuario")
    public ResponseEntity<?> buscarPorMail(@RequestBody DtoBuscarUsuario buscarUsuario) {
        var usuario = userService.buscarPorId(buscarUsuario.id());
        return ResponseEntity.ok(usuario);
    }


}
