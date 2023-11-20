package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CidadesRepository extends JpaRepository <Cidades, UUID> {
    //List<Cidades> findByNomeStartingWith (String termo);
}
