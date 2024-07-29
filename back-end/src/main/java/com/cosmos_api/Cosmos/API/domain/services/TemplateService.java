package com.cosmos_api.Cosmos.API.domain.services;

import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoBuscaIdTemplate;
import com.cosmos_api.Cosmos.API.aplication.dto.template.DtoRegistroTemplate;
import com.cosmos_api.Cosmos.API.domain.entities.template.Template;
import com.cosmos_api.Cosmos.API.domain.repository.TemplateRepository;
import com.cosmos_api.Cosmos.API.domain.repository.UsuarioRepository;
import com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones.EmailAlreadyExistsException;
import com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateSlug(String name, String email) {
        String emailPrefix = email.split("@")[0];
        String baseSlug = name.toLowerCase().replaceAll("[^a-z0-9]", "-") +
                emailPrefix.toLowerCase().replaceAll("[^a-z0-9]", "-");
        String slug = baseSlug;
        int counter = 1;
        while (templateRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + counter;
            counter++;
        }
        return slug;
    }


    public Template registrarTemplate(DtoRegistroTemplate dtoRegistroTemplate) {
        if(templateRepository.findByUsuarioId(dtoRegistroTemplate.usuarioId()) != null) {
           throw new EmailAlreadyExistsException("Ya existe un template para este usuario");
        }
        var usuario = usuarioRepository.findById(dtoRegistroTemplate.usuarioId());
        String slug = generateSlug(dtoRegistroTemplate.name(), usuario.get().getEmail());
        Template template = new Template();
        template.setSlug(slug);
        template.setVariant(dtoRegistroTemplate.variant());
        template.setName(dtoRegistroTemplate.name());
        template.setImage(dtoRegistroTemplate.image());
        template.setUsuario(usuario.get());

        template = templateRepository.save(template);

        return template;

    }

    public Template buscarTemplate(DtoBuscaIdTemplate idTemplate) {
        var template = templateRepository.findByUsuarioId(idTemplate.usuarioId());
        if (template == null) {
            throw new UsuarioNoEncontradoException("Template no encontrado");
        }
        return template;
    }
}

