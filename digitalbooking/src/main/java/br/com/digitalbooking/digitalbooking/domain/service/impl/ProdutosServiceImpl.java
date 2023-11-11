package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.repository.ProdutosRepository;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosServiceImpl(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @Override
    public Produtos criarProduto(Produtos produtos) {
        return null;
    }

    @Override
    public List<Produtos> buscarProdutos(String termo) {
        return null;
    }

    @Override
    public Produtos buscarProdutoPorId(UUID id) {
        return null;
    }

    @Override
    public Produtos atualizarProduto(UUID id, Produtos produtos) {
        return null;
    }

    @Override
    public void deletarProduto(UUID id) {

    }
}
