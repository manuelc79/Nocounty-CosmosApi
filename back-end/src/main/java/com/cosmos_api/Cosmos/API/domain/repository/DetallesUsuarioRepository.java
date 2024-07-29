package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.user.DetallesUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallesUsuarioRepository extends JpaRepository<DetallesUsuario, Long> {
//    @Query("""
//            SELECT d FROM DetallesUsuario d
//            WHERE d.usuarioId = :id
//            """)
//    DetallesUsuario buscarUsuario(@Param("usuarioId") Long id);

    DetallesUsuario findByUsuarioId(Long usuario);
}
