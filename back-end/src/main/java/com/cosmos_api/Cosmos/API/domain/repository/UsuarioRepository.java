package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String username);

}
