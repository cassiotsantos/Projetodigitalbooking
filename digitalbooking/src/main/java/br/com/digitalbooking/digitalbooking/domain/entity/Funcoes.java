package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "funcoes")
public class Funcoes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    @OneToMany
    @JoinColumn(
            name= "usuarios_id",
            foreignKey =
            @ForeignKey(name = "fk_funcoes_usuarios")
    )
    private List<Usuarios> usuariosList;


    public Funcoes() {
    }
}
