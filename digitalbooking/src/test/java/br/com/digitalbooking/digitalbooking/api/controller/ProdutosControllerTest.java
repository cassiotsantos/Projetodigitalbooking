package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.domain.entity.*;
import br.com.digitalbooking.digitalbooking.domain.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutosController.class)
class ProdutosControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  JwtService jwtService;

  @MockBean
  UserService userService;

  @MockBean
  ProdutosService service;

  @MockBean
  private CidadesService cidadesService;

  @MockBean
  private CategoriasService categoriasService;

  @Test
  @WithMockUser
  void shouldGetByIdProdutoSuccessfully() throws Exception {
    UUID uuid = UUID.randomUUID();
    Produtos produtos = new Produtos(uuid, "Produto Teste", "Descricao Teste", Arrays.asList(new Imagens(),
        new Imagens()), new HashSet<>(Arrays.asList(new Caracteristicas(), new Caracteristicas())), new Cidades(), new Categorias(), "123.456", "789.012", LocalDateTime.now());

    given(service.buscarProdutoPorId(uuid))
        .willReturn(produtos);

    var response = mockMvc
        .perform(get("/v1/produtos/{id}", uuid))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  @WithMockUser
  void shouldGetProdutosSuccessfully() {

  }

  @Test
  void shouldCreateProdutosSuccessfully() {
  }

  @Test
  void shouldUpdateProdutosSuccessfully() {
  }

  @Test
  void shouldGetByCategoriaProductsSuccessfully() {
  }

  @Test
  void shouldGetByCidadeProdutosSuccessfully() {
  }

  @Test
  void shouldDeleteProductSuccessfully() {
  }
}