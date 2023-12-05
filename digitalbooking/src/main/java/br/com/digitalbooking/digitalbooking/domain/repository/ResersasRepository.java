package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResersasRepository extends JpaRepository <Reservas, UUID> {

    List<Reservas> findByProdutosId(UUID produtoId);
    List<Reservas> findByUsuario(UUID usuarioId);

}
