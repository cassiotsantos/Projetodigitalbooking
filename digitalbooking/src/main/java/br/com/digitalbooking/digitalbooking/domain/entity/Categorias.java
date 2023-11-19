package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "categorias")
public class Categorias {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String nome;
  private String urlImage;
  private String descricao;
  private EnumQualificacao qualificacao;
  @CreationTimestamp
  private LocalDateTime createdAt;

  public Categorias() {
  }
}