package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProdutosService {
    Produtos criarProduto (Produtos produtos,UUID categoriaId, UUID cidadesId, List<UUID> imagensId, Set<UUID> caracteristicasProdutoId);
    List<Produtos> buscarProdutos(String termo);
    Produtos buscarProdutoPorId (UUID id);
    List<Produtos> listaProdutoPorCategoria (String nome);
    List<Produtos> listaProdutoPorCidade (String nome);
    Produtos atualizarProduto (UUID id, Produtos produtos);
    void deletarProduto(UUID id);
}
