package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoBuscaIdTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoRegistroTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoRespuestaTemplate;
import com.cosmos_api.Cosmos.API.domain.services.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo Template en la base de datos")
    public ResponseEntity<?> registrarTemplate(@RequestBody @Valid DtoRegistroTemplate dtoRegistroTemplate) {
        var template = templateService.registrarTemplate(dtoRegistroTemplate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DtoRespuestaTemplate(
                        template.getId(),
                        template.getName(),
                        template.getImage(),
                        template.getSlug(),
                        template.getVariant().toString()));
    }

    @GetMapping
    @Operation(summary = "Busca todos los templates de un usuario según usuarioId")
    public ResponseEntity<?> mostrarPorId(@RequestBody DtoBuscaIdTemplate idUsuario) {
        var templates = templateService.buscarTodosLosTemplates(idUsuario.usuarioId());
        return ResponseEntity.ok(templates.stream()
                .map(template -> new DtoRespuestaTemplate(
                    template.getId(),
                    template.getName(),
                    template.getImage(),
                    template.getSlug(),
                    template.getVariant().toString()))
                .collect(Collectors.toList()));
    }

}
