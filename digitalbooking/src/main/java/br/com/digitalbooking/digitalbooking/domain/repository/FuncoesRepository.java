package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Funcoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncoesRepository extends JpaRepository<Funcoes, UUID> {
}
