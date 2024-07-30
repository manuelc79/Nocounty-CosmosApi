package com.cosmos_api.Cosmos.API.domain.entities.products;

import com.cosmos_api.Cosmos.API.domain.entities.Groups;
import com.cosmos_api.Cosmos.API.domain.entities.user.DetallesUsuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproducts;

    @NotNull
    private String name;

    private String brand;

    private String category;

    private String code;

    private double price;

    private int stock;

    private String color;

    private String discount;

    private String tag;

    private String description;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private List<Images> imageList = new ArrayList<>();

    @ManyToOne(targetEntity = DetallesUsuario.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "detalle_usuario_id")
    @JsonBackReference
    private DetallesUsuario detallesUsuario;

    @ManyToOne(targetEntity = Groups.class, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Groups groups;
}
