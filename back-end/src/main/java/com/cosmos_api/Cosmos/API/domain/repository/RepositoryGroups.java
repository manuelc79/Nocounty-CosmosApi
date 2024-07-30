package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.Groups;
import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryGroups extends JpaRepository<Groups, Long>{
    public Optional<Groups> findByIdGroups(long id);

    public Optional<Groups> findByName(String title);

    public boolean existsByName(String title);
}
