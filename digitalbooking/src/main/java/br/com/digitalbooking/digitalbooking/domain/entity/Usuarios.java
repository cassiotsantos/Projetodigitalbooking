package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "funcoes")

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    @OneToMany
    @JoinColumn(
            name= "funcoes_id",
            foreignKey =
            @ForeignKey(name = "fk_usuarios_funcoes")
    )


    public Usuarios() {
    }


}

