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
@Table(name = "categorias")
public class Categorias {
  public String nome;
  public String urlImage;
  public String descricao;
  public String qualificacao;
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  @CreationTimestamp
  private LocalDateTime createdAt;

  public Categorias(String nome, String urlImage, String descricao, String qualificacao, UUID id, LocalDateTime createdAt) {
    this.nome = nome;
    this.urlImage = urlImage;
    this.descricao = descricao;
    this.qualificacao = qualificacao;
    this.id = id;
    this.createdAt = createdAt;
  }

  public Categorias() {
  }
}