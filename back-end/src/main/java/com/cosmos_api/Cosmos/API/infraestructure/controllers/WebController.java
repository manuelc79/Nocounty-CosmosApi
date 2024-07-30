package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.web.DtoBuscaIdWeb;
import com.cosmos_api.Cosmos.API.aplication.dto.web.DtoRegistraWeb;
import com.cosmos_api.Cosmos.API.aplication.dto.web.DtoRespuestaWeb;
import com.cosmos_api.Cosmos.API.domain.services.WebService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebService webService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo Web en la base de datos")
    public ResponseEntity<?> registrarWeb(@RequestBody @Valid DtoRegistraWeb dtoRegistraWeb) {
        var web = webService.registrarWeb(dtoRegistraWeb);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DtoRespuestaWeb(
                        web.getId(),
                        web.getName(),
                        web.getImage(),
                        web.getSlug(),
                        web.getVariant().toString(),
                        web.getGrupoId()));
    }

    @PostMapping("/find")
    @Operation(summary = "Busca todas las Webs de un usuario según usuarioId")
    public ResponseEntity<?> mostrarPorId(@RequestBody DtoBuscaIdWeb idUsuario) {
        var webs = webService.buscarTodasLasWebs(idUsuario.usuarioId());
        return ResponseEntity.ok(webs.stream()
                .map(web -> new DtoRespuestaWeb(
                    web.getId(),
                    web.getName(),
                    web.getImage(),
                    web.getSlug(),
                    web.getVariant().toString(),
                    web.getGrupoId()))
                .collect(Collectors.toList()));
    }

}
