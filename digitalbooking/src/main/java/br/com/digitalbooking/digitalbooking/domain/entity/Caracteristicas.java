package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private String icone;
    private EnumQualificacao qualificacao;

    public Caracteristicas() {

    }

}
