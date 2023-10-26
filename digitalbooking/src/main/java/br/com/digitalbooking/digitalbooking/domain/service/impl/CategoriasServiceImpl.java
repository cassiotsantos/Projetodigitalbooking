package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.exception.NotFoundException;
import br.com.digitalbooking.digitalbooking.domain.repository.CategoriasRepository;
import br.com.digitalbooking.digitalbooking.domain.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriasServiceImpl implements CategoriasService {

    private final CategoriasRepository categoriasRepository;
    @Autowired
    public CategoriasServiceImpl(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public Categorias criarCategoria(Categorias categorias) {
        return this.categoriasRepository.save(categorias);
    }

    @Override
    public List<Categorias> buscarCategorias(String termo) {
        return this.categoriasRepository.findByNameStartingWith(termo);
    }

    @Override
    public Categorias buscarCategoriasPorId(UUID id) {
        try{ return categoriasRepository.findById(id).orElseThrow();}
        catch (Exception e){ throw new NotFoundException(id);
     }};

    @Override
    public Categorias atualizarCategoria(UUID id, Categorias categorias) {
        try{ categoriasRepository.findById(id).orElseThrow();
        } catch (Exception e) {throw new NotFoundException(id);}
        return categoriasRepository.save(categorias);
    }
    @Override
    public void deletarCategoria(UUID id) {
        try{categoriasRepository.findById(id).orElseThrow();
            categoriasRepository.deleteById(id);}
        catch(Exception e) {throw new NotFoundException(id);};

    }
}
