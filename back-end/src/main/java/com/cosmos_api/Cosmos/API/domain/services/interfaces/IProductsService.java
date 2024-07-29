package com.cosmos_api.Cosmos.API.domain.services.interfaces;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoProducts;
import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import java.util.List;
import java.util.Optional;


public interface IProductsService {
    public List<Products> listAlllProducts();

    public Products getOneProducts(Long idproducts);

    public Products saveProducts(dtoProducts dtoproducts);

    public boolean deleteProducts(Long idproducts);

    public boolean existsByIdProducts(Long idproducts);

    public Optional<Products> getByNameProducts(String Title);

    public Products editProducts(Long idproducts, dtoProducts dtoproducts);

    public boolean existsByNameProducts(String Title);
}
