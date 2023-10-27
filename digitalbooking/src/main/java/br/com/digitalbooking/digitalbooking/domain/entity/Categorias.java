package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="categorias")
public class Categorias {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    public String nome;
    public String urlImage;
    public String descricao;
    public String qualificacao;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
