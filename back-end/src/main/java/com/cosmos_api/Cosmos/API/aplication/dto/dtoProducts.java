package com.cosmos_api.Cosmos.API.aplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class dtoProducts {

    @JsonIgnore
    private Long idproducts;

    private String name;

    private String brand;

    private String category;

    private String code;

    private double price;

    private int stock;

    private String color;

    private int discount;

    private String tag;

    private String description;

    private List<String> images;

}
