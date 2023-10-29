package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.repository.CategoriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CategoriasServiceImplTest {
  @InjectMocks
  CategoriasServiceImpl service;

  @Mock
  CategoriasRepository repository;

  Categorias categoria;

  @BeforeEach
  void setUp() {
    categoria = new Categorias(
        "nome",
        "urlImage",
        "descricao",
        "qualificacao",
        UUID.randomUUID(),
        LocalDateTime.now()
    );
  }

  @Test
  void criar() {

    given(repository
        .save(any(Categorias.class)))
        .willReturn(categoria);

    Categorias saveCategoria = service.criar(this.categoria);

    assertNotNull(saveCategoria);
    assertTrue(saveCategoria.getId().getClass().equals(UUID.class));
    assertFalse(saveCategoria.getId().toString().isBlank());
    assertFalse(saveCategoria.getNome().isBlank());

  }

  @Test
  void buscarCategorias() {
    given(repository
        .findByNomeStartingWith(any(String.class)))
        .willReturn(List.of(categoria));

    List<Categorias> categorias = service.buscarCategorias("nome");

    assertNotNull(categorias);
    assertTrue(categorias.get(0).getId().getClass().equals(UUID.class));
    assertTrue(categorias.get(0).getNome().equals("nome"));
    assertFalse(categorias.get(0).getId().toString().isBlank());

  }

  @Test
  void buscarCategoriasPorId() {
    given(repository
        .findById(any(UUID.class)))
        .willReturn(Optional.of(categoria));

    Categorias categorias = service.buscarCategoriasPorId(categoria.getId());

    assertNotNull(categorias);
    assertTrue(categorias.getId().getClass().equals(UUID.class));
    assertTrue(categorias.getNome().equals("nome"));
    assertFalse(categorias.getId().toString().isBlank());

  }
}