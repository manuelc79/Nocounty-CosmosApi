package com.cosmos_api.Cosmos.API.domain.repository;


import com.cosmos_api.Cosmos.API.domain.entities.web.Web;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebRepository extends JpaRepository<Web, Long> {

    List<Web> findByUsuarioId(Long usuarioId);

    boolean existsBySlug(String slug);


}
