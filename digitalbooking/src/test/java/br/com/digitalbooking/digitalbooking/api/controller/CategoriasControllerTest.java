package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.service.impl.CategoriasServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CategoriasControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  CategoriasServiceImpl categoriasService;

  @Test
  void buscarCategoriasPorId() throws Exception {

    UUID uuid = UUID.randomUUID();

    Categorias categorias = new Categorias();
    categorias.setId(uuid);
    categorias.setNome("Teste");
    categorias.setUrlImage("Teste");
    categorias.setDescricao("Teste");
    categorias.setQualificacao("Teste");
    categorias.setCreatedAt(LocalDateTime.now());

    given(categoriasService
        .buscarCategoriasPorId(uuid))
        .willReturn(categorias);

    ResultActions response = mockMvc
        .perform(get("/v1/categorias/{id}", uuid));
    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id")
            .value(categorias.getId().toString()))
        .andExpect(jsonPath("$.nome")
            .value(categorias.getNome()))
        .andExpect(jsonPath("$.urlImage")
            .value(categorias.getUrlImage()))
        .andExpect(jsonPath("$.descricao")
            .value(categorias.getDescricao()))
        .andExpect(jsonPath("$.qualificacao")
            .value(categorias.getQualificacao()))
        .andExpect(jsonPath("$.createdAt")
            .value(categorias.getCreatedAt().toString().substring(0, 27)));
  }

  @Test
  void buscarCategorias() throws Exception {

    List<Categorias> categorias = List.of(
        new Categorias(
            "Teste",
            "Teste",
            "Teste",
            "Teste",
            UUID.randomUUID(),
            LocalDateTime.now()
        ),
        new Categorias(
            "Teste",
            "Teste",
            "Teste",
            "Teste",
            UUID.randomUUID(),
            LocalDateTime.now()
        )
    );

    given(categoriasService.buscarCategorias("Teste"))
        .willReturn(categorias);

    ResultActions response = mockMvc
        .perform(get("/v1/categorias?termo=Teste"));

    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.categorias[0].id")
            .value(categorias.get(0).getId().toString()))
        .andExpect(jsonPath("$.categorias[0].nome")
            .value(categorias.get(0).getNome()))
        .andExpect(jsonPath("$.categorias[0].urlImage")
            .value(categorias.get(0).getUrlImage()))
        .andExpect(jsonPath("$.categorias[0].descricao")
            .value(categorias.get(0).getDescricao()))
        .andExpect(jsonPath("$.categorias[0].qualificacao")
            .value(categorias.get(0).getQualificacao()))
        .andExpect(jsonPath("$.categorias[0].createdAt")
            .value(categorias.get(0).getCreatedAt().toString().substring(0, 27)));

    response
        .andExpect(jsonPath("$.categorias[1].id")
            .value(categorias.get(1).getId().toString()))
        .andExpect(jsonPath("$.categorias[1].nome")
            .value(categorias.get(1).getNome()))
        .andExpect(jsonPath("$.categorias[1].urlImage")
            .value(categorias.get(1).getUrlImage()))
        .andExpect(jsonPath("$.categorias[1].descricao")
            .value(categorias.get(1).getDescricao()))
        .andExpect(jsonPath("$.categorias[1].qualificacao")
            .value(categorias.get(1).getQualificacao()))
        .andExpect(jsonPath("$.categorias[1].createdAt")
            .value(categorias.get(1).getCreatedAt().toString().substring(0, 27)));


  }

  @Test
  void criarCategorias() throws Exception {
    Categorias categorias = new Categorias(
        "Teste",
        "Teste",
        "Teste",
        "Teste",
        UUID.randomUUID(),
        LocalDateTime.now()
    );

    given(categoriasService.criar(any(Categorias.class)))
        .willReturn(categorias);

    ResultActions response = mockMvc
        .perform(post("/v1/categorias")
            .contentType("application/json")
            .content(mapper.writeValueAsString(categorias)));

    response
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$")
            .value(categorias.getId().toString()));
  }
}