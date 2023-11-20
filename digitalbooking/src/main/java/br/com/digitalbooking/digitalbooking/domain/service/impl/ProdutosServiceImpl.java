package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.*;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.*;
import br.com.digitalbooking.digitalbooking.domain.service.ProdutosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutosServiceImpl implements ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final CategoriasRepository categoriasRepository;
    private final CidadesRepository cidadesRepository;
    private final CaracteristicasRepository caracteristicasRepository;
    private final ImagensRepository imagensRepository;

    @Override
    public Produtos criarProduto(Produtos produtos, UUID categoriaId, UUID cidadesId, List<UUID> imagensId, Set<UUID> caracteristicasProdutoId) {
        Categorias categorias = categoriasRepository.findById(categoriaId).orElseThrow(() -> new NotFoundException(categoriaId));
        produtos.setCategorias(categorias);
        Cidades cidades = cidadesRepository.findById(cidadesId).orElseThrow(() -> new NotFoundException(cidadesId));
        produtos.setCidades(cidades);
        List<Imagens> imagens = imagensRepository.findAllById(imagensId);
        produtos.setImagensList(imagens);
        List<Caracteristicas> caracteristicas = caracteristicasRepository.findAllById(caracteristicasProdutoId);
        Set<Caracteristicas> caracteristicasSet = new HashSet<>(caracteristicas);
        produtos.setProdutosCaracteristicas(caracteristicasSet);
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
    public List<Produtos> listaProdutoPorCategoria(String nome) {
        return produtosRepository.findByCategoriasNome(nome);
    }

    @Override
    public List<Produtos> listaProdutoPorCidade(String nome) {
        return produtosRepository.findByCidadesNome(nome);
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
