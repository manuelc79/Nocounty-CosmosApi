package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProducts extends JpaRepository<Products, Long>{
    public Optional<Products> findByIdproducts(long id);

    public Optional<Products> findByName(String title);

    public boolean existsByName(String title);
}
