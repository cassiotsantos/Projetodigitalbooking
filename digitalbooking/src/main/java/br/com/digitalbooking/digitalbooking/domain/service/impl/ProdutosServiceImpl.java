package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.ProdutosRepository;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;
    @Override
    public Produtos criarProduto(Produtos produtos) {
        return this.produtosRepository.save(produtos);
    }

    @Override
    public List<Produtos> buscarProdutos(String termo) {
        return this.produtosRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Produtos buscarProdutoPorId(UUID id) {
        try{ return produtosRepository.findById(id).orElseThrow();}
        catch (Exception e){ throw new NotFoundException(id);}
    }

    @Override
    public Produtos atualizarProduto(UUID id, Produtos produtos) {
        try{ produtosRepository.findById(id).orElseThrow();
        } catch (Exception e) {throw new NotFoundException(id);}
        return produtosRepository.save(produtos);
    }

    @Override
    public void deletarProduto(UUID id) {
        try{produtosRepository.findById(id).orElseThrow();
            produtosRepository.deleteById(id);}
        catch(Exception e) {throw new NotFoundException(id);}
    }
}
