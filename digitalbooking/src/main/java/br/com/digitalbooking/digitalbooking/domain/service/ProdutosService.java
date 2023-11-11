package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;

import java.util.List;
import java.util.UUID;

public interface ProdutosService {
    Produtos criarProduto (Produtos produtos);
    List<Produtos> buscarProdutos(String termo);
    Produtos buscarProdutoPorId (UUID id);
    Produtos atualizarProduto (UUID id, Produtos produtos);
    void deletarProduto(UUID id);
}
