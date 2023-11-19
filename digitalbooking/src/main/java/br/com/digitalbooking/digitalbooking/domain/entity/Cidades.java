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
@Table(name = "cidades")
public class Cidades {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String cidade;
    private String pais;

    public Cidades() {
    }
}
