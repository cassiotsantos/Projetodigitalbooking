package br.com.digitalbooking.digitalbooking.domain.repository;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

List<Produtos> findByNomeStartingWith (String termo);
List<Produtos> findByCategoriasNome (String nome);
List<Produtos> findByCidadeNome(String nome);
}
