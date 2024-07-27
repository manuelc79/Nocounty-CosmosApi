package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoProducts;
import com.cosmos_api.Cosmos.API.domain.entities.Products;
import com.cosmos_api.Cosmos.API.domain.services.interfaces.IProductsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {
     @Autowired
    private final IProductsService productsService;
     
     @GetMapping
    public ResponseEntity<List<Products>> listProducts() {
        List<Products> list = productsService.listAlllProducts();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getByIdProducts(@PathVariable("id") long id) {
        Products products = productsService.getOneProducts(id);
        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable("id") long id) {
        boolean pack = this.productsService.deleteProducts(id);
        //SI SE BORRO VA A DEVOLVER UN TRUE Y VA A ENTRAR EN ESTE BLOQUE Y VA A DEVOLVER UN NO_CONTENT
        if (pack) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProducts(@RequestBody dtoProducts dtoproducts) {
        return new ResponseEntity<>(this.productsService.saveProducts(dtoproducts), HttpStatus.CREATED);
    }

    
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProducts(@PathVariable("id") long id, @RequestBody dtoProducts dtoproducts) {
        Products products = this.productsService.editProducts(id, dtoproducts);
        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
