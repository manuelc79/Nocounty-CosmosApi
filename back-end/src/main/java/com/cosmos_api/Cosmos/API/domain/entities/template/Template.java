package com.cosmos_api.Cosmos.API.domain.entities.template;

import com.cosmos_api.Cosmos.API.domain.entities.user.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "templates")
@Entity(name = "Template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String slug;

    @Column(name = "variant")
    @Enumerated(EnumType.STRING)
    private Variants variant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonManagedReference
    private Usuario usuario;

}
