package com.cosmos_api.Cosmos.API.domain.repository;

import com.cosmos_api.Cosmos.API.domain.entities.products.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryImages extends JpaRepository<Images, Long>{

}
