package com.cosmos_api.Cosmos.API.domain.services;

import com.cosmos_api.Cosmos.API.aplication.dto.web.DtoRegistraWeb;
import com.cosmos_api.Cosmos.API.domain.entities.web.Web;
import com.cosmos_api.Cosmos.API.domain.repository.UsuarioRepository;
import com.cosmos_api.Cosmos.API.domain.repository.WebRepository;

import com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {

    @Autowired
    private WebRepository webRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateSlug(String name, String email) {
        String emailPrefix = email.split("@")[0];
        String baseSlug = name.toLowerCase().replaceAll("[^a-z0-9]", "-") +
                emailPrefix.toLowerCase().replaceAll("[^a-z0-9]", "-");
        String slug = baseSlug;
        int counter = 1;
        while (webRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + counter;
            counter++;
        }
        return slug;
    }

    public Web registrarWeb(DtoRegistraWeb dtoRegistraWeb) {

        var usuario = usuarioRepository.findById(dtoRegistraWeb.usuarioId());
        String slug = generateSlug(dtoRegistraWeb.name(), usuario.get().getEmail());
        Web web = new Web();
        web.setSlug(slug);
        web.setVariant(dtoRegistraWeb.variant());
        web.setName(dtoRegistraWeb.name());
        web.setImage(dtoRegistraWeb.image());
        web.setUsuario(usuario.get());
        web.setGrupoId(dtoRegistraWeb.grupoId());

        web = webRepository.save(web);
        return web;

    }

    public List<Web> buscarTodasLasWebs(Long usuarioId) {
        var web = webRepository.findByUsuarioId(usuarioId);
        if (web == null) {
            throw new UsuarioNoEncontradoException("No se encontró Templates para el Usuario solicitado");
        }
        return web;
    }

    public Web buscarSlug(String slug, Long usuarioId) {
        var template = webRepository.findByUsuarioIdAndSlug(usuarioId, slug);
        return template;
    }
}

