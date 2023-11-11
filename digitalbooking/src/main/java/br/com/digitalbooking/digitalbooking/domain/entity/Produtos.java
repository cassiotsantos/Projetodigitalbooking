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
@Table(name = "produtos")
public class Produtos {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    public String nome;
    public String descricao;
    public String latitude;
    public String longitude;

    public Produtos() {
    }
}
