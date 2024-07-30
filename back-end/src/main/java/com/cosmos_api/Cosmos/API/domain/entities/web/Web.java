package com.cosmos_api.Cosmos.API.domain.entities.web;

import com.cosmos_api.Cosmos.API.domain.entities.template.Variants;
import com.cosmos_api.Cosmos.API.domain.entities.user.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "webs")
@Entity(name = "Web")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Web {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "image", length = 1398368, columnDefinition = "VARCHAR(1398368)")
    private String image;

    private String slug;

    @Column(name = "variant")
    @Enumerated(EnumType.STRING)
    private Variants variant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonBackReference
    private Usuario usuario;

    private Long grupoId;

}
