package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservasRepository extends JpaRepository <Reservas, UUID> {

    List<Reservas> findByProdutosId(UUID produtoId);
    List<Reservas> findByUsuarioId(UUID usuarioid);

}
