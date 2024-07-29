package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoBuscaIdTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoRegistroTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoRespuestaTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoSlugTemplate;
import com.cosmos_api.Cosmos.API.domain.services.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Busca un template según UsuarioId")
    public ResponseEntity<?> mostrarPorId(@RequestBody DtoBuscaIdTemplate idTemplate) {
        var template = templateService.buscarTemplate(idTemplate);
        return ResponseEntity.ok(new DtoRespuestaTemplate(
                template.getId(),
                template.getName(),
                template.getImage(),
                template.getSlug(),
                template.getVariant().toString()));
    }

    @GetMapping("/slug")
    @Operation(summary = "Busca un Slug según UsuarioId")
    public ResponseEntity<?> buscarSlug(@RequestBody DtoBuscaIdTemplate idTemplate) {
        var slug = templateService.buscarTemplate(idTemplate);
        return ResponseEntity.ok(new DtoSlugTemplate(slug.getSlug()));
    }
}
