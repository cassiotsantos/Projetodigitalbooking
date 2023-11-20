package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "produtos")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    @OneToMany
    @JoinColumn(
            name= "imagens_id",
            foreignKey =
            @ForeignKey(name = "fk_produto_imagens")
    )
    private List<Imagens> imagensList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name =" produtos_caracteristicas",
                joinColumns =
                @JoinColumn(name = "id_produtos"),
                inverseJoinColumns =
                @JoinColumn(name = "id_caracteristicas")
    )
    private Set<Caracteristicas> produtosCaracteristicas;
    @ManyToOne
    @JoinColumn(
            name="cidade_id",
            foreignKey =
            @ForeignKey(name = "fk_produto_cidade")
    )
    private Cidades cidades;
    @ManyToOne
    @JoinColumn( name="categorias_id",
            foreignKey =
            @ForeignKey(name ="fk_produtos_categorias")
    )
    private Categorias categorias;
    private String latitude;
    private String longitude;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Produtos() {
    }
}
