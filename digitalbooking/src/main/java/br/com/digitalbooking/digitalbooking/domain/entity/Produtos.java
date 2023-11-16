package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "produtos")
public class Produtos {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private String descricao;
    @OneToMany
    @JoinColumn(
            name= "imagens_id",
            foreignKey =
            @ForeignKey(name = "fk_produto_imagens")
    )
    private Imagens imagens;
    @ManyToMany
    @JoinColumn(
            name ="caracteristicas_id",
            foreignKey =
            @ForeignKey(name = "fk_produto_caracteristicas")
    )
    private Caracteristicas caracteristicas;
    @ManyToOne
    @JoinColumn(
            name="",
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
    private LocalDateTime createdAt;

    public Produtos() {
    }
}
