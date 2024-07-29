package com.cosmos_api.Cosmos.API.domain.entities.user;

import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "detalle_usuario")
@Entity(name = "DetallesUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetallesUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String userName;
    private Boolean active;

    // relación OneToOne con Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private Usuario usuario;

    @OneToMany(mappedBy = "detallesUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Products> productList = new ArrayList<>();
    
}
