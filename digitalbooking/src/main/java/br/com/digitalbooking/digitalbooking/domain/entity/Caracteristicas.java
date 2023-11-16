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
@Table(name = "caracteristicas")
public class Caracteristicas {
    public String nome;
    public String icone;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    public Caracteristicas() {

    }

    public Caracteristicas(UUID id, String nome, String icone) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
    }
}
