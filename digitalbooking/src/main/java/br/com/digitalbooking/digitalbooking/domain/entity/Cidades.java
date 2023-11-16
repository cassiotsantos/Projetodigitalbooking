package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public String nome;
    public String pais;

    public Cidades(UUID id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Cidades() {
    }
}
