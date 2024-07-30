package com.cosmos_api.Cosmos.API.domain.services.implementation;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoProducts;
import com.cosmos_api.Cosmos.API.domain.entities.products.Images;
import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import com.cosmos_api.Cosmos.API.domain.repository.RepositoryImages;
import com.cosmos_api.Cosmos.API.domain.repository.RepositoryProducts;
import com.cosmos_api.Cosmos.API.domain.services.interfaces.IProductsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsService implements IProductsService {

    @Autowired
    private final RepositoryProducts productsRepository;
    
    @Autowired
    private RepositoryImages imagesRepository;

    @Override
    public List<Products> listAlllProducts() {
        return this.productsRepository.findAll();
    }

    @Override
    public Products getOneProducts(Long idproducts) {
        return productsRepository.findByIdproducts(idproducts)
                .orElseThrow(() -> new RuntimeException("products with that ID dont exist"));
    }

    @Override
    public Products saveProducts(dtoProducts dtoproducts) {
         List<Images> imageList = new ArrayList<>();
         
            for (String imageBase64 : dtoproducts.getImages()) {
            Images image = Images.builder()
                    .image(imageBase64)
                    .build();
            imageList.add(image);
        }
            
        Products products = Products.builder()
                .name(dtoproducts.getName())
                .description(dtoproducts.getDescription())
                .brand(dtoproducts.getBrand())
                .category(dtoproducts.getCategory())
                .code(dtoproducts.getCode())
                .price(dtoproducts.getPrice())
                .stock(dtoproducts.getStock())
                .color(dtoproducts.getColor())
                .discount(dtoproducts.getDiscount())
                .tag(dtoproducts.getTag())
                .imageList(imageList)
                .build();
        
        for (Images image : imageList) {
            image.setProducts(products); // Asignar la relación inversa
        }
        
        return productsRepository.save(products);
    }

    @Override
    public boolean deleteProducts(Long idproducts) {
        Optional<Products> productsDelete = this.productsRepository.findById(idproducts);
        if (productsDelete.isPresent()) {
            this.productsRepository.deleteById(idproducts);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsByIdProducts(Long idproducts) {
        return productsRepository.existsById(idproducts);
    }

    @Override
    public Optional<Products> getByNameProducts(String name) {
        return productsRepository.findByName(name);
    }

    @Override
    public Products editProducts(Long idproducts, dtoProducts dtoproducts) {
        
        Products products = this.getOneProducts(idproducts);
        
        products.setName(dtoproducts.getName());
        products.setDescription(dtoproducts.getDescription());
        products.setBrand(dtoproducts.getBrand());
        products.setCategory(dtoproducts.getCategory());
        products.setCode(dtoproducts.getCode());
        products.setPrice(dtoproducts.getPrice());
        products.setStock(dtoproducts.getStock());
        products.setColor(dtoproducts.getColor());
        products.setDiscount(dtoproducts.getDiscount());
        products.setTag(dtoproducts.getTag());
        
        List<Images> newImageList = new ArrayList<>();
        for (String imageBase64 : dtoproducts.getImages()) {
            Images image = Images.builder()
                    .image(imageBase64)
                    .products(products) 
                    .build();
            newImageList.add(image);
        }

        List<Images> oldImageList = products.getImageList();
        if (oldImageList != null) {
            for (Images oldImage : oldImageList) {
                imagesRepository.delete(oldImage); 
            }
        }

        products.setImageList(newImageList);
        
        return productsRepository.save(products);
        
    }

    @Override
    public boolean existsByNameProducts(String Name) {
        return productsRepository.existsByName(Name);
    }

}
