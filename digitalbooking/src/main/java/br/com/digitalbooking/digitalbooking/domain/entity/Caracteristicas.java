package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "caracteristicas")
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String icone;
    public Caracteristicas() {

    }

}
