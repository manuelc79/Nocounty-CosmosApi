package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findByUsuarioId(Long usuarioId);

    boolean existsBySlug(String slug);

    Template findByUsuarioIdAndSlug(Long usuarioId, String slug);
}
