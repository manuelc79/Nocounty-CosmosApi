package com.cosmos_api.Cosmos.API.aplication.dto;

import java.util.List;

import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class dtoGroups {

    private String name;

    private String description;
    
     private List<Products> products;

}
