package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "cidades")
public class Cidades {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private String pais;

    public Cidades(UUID id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Cidades() {
    }
}
