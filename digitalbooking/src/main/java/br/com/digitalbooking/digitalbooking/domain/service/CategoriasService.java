package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;

import java.util.List;
import java.util.UUID;

public interface CategoriasService {
    Categorias criarCategoria(Categorias categorias);
    List<Categorias> buscarCategorias(String termo);
    Categorias buscarCategoriasPorId(UUID id);
    Categorias atualizarCategoria(UUID id, Categorias categorias);
    void deletarCategoria(UUID id);
}
