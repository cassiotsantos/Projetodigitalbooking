package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.EnumQualificacao;
import br.com.digitalbooking.digitalbooking.domain.service.JwtService;
import br.com.digitalbooking.digitalbooking.domain.service.UserService;
import br.com.digitalbooking.digitalbooking.domain.service.impl.CategoriasServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriasController.class)
class CategoriasControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;


  @MockBean
  CategoriasServiceImpl categoriasService;

  @MockBean
  JwtService jwtService;

  @MockBean
  UserService userService;


  @Test
  @WithMockUser
  void shouldGetByIdCategorySuccessfully() throws Exception {

    UUID uuid = UUID.randomUUID();

    Categorias categorias = new Categorias();
    categorias.setId(uuid);
    categorias.setNome("Teste");
    categorias.setUrlImage("Teste");
    categorias.setDescricao("Teste");
    categorias.setQualificacao(EnumQualificacao.UM);
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
            .value(categorias.getQualificacao().toString()));
  }

  @Test
  @WithMockUser
  void shouldGetListCategorySuccessfully() throws Exception {

    List<Categorias> categorias = List.of(
        new Categorias(
            UUID.randomUUID(),
            "Teste",
            "Teste",
            "Teste",
            EnumQualificacao.UM,
            LocalDateTime.now()
        ),
        new Categorias(
            UUID.randomUUID(),
            "Teste",
            "Teste",
            "Teste",
            EnumQualificacao.UM,
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
            .value(categorias.get(0).getQualificacao().toString()));

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
            .value(categorias.get(1).getQualificacao().toString()));
  }

  @Test
  @WithMockUser
  void shouldCreateCategorySuccessfully() throws Exception {
    CategoriasRequest request = new CategoriasRequest(
        "Teste",
        "Teste",
        "Testedecumentu",
        EnumQualificacao.UM,
        LocalDateTime.now()
    );
    Categorias categorias = new Categorias(
        UUID.randomUUID(),
        "Teste",
        "Teste",
        "Testedecumentu",
        EnumQualificacao.UM,
        request.getCreatedAt()
    );


    given(categoriasService.criarCategorias(any(Categorias.class)))
        .willReturn(categorias);

    ResultActions response = mockMvc
        .perform(post("/v1/categorias")
            .with(csrf())
            .contentType("application/json")
            .content(mapper.writeValueAsString(request)));

    response
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.nome")
            .value(categorias.getNome()))
        .andExpect(jsonPath("$.urlImage")
            .value(categorias.getUrlImage()))
        .andExpect(jsonPath("$.descricao")
            .value(categorias.getDescricao()))
        .andExpect(jsonPath("$.qualificacao")
            .value(categorias.getQualificacao().toString()))
        .andExpect(jsonPath("$.createdAt")
            .exists());
  }

  @Test
  @WithMockUser
  void shouldUpdateCategorySuccessfully() throws Exception {
    UUID uuid = UUID.randomUUID();

    CategoriasRequest newCategoryDetails = new CategoriasRequest("NewName", "NewUrl", "NewDescription",
        EnumQualificacao.DOIS, LocalDateTime.now());

    Categorias newCategory = new Categorias(uuid, newCategoryDetails.getNome(), newCategoryDetails.getUrlImage(),
        newCategoryDetails.getDescricao(), newCategoryDetails.getQualificacao(), newCategoryDetails.getCreatedAt());

    given(categoriasService.atualizarCategoria(uuid, newCategory))
        .willReturn(newCategory);

    given(categoriasService.buscarCategoriasPorId(uuid))
        .willReturn(newCategory);

    ResultActions response = mockMvc
        .perform(put("/v1/categorias/{id}", uuid)
            .with(csrf())
            .contentType("application/json")
            .content(mapper.writeValueAsString(newCategoryDetails)));

    response
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(newCategory.getId().toString()))
        .andExpect(jsonPath("$.nome").value(newCategory.getNome()))
        .andExpect(jsonPath("$.urlImage").value(newCategory.getUrlImage()))
        .andExpect(jsonPath("$.descricao").value(newCategory.getDescricao()))
        .andExpect(jsonPath("$.qualificacao").value(newCategory.getQualificacao().toString()))
        .andExpect(jsonPath("$.createdAt").exists());
  }

  @Test
  @WithMockUser
  void shouldDeleteCategorySuccessfully() throws Exception {

    UUID uuid = UUID.randomUUID();

    doNothing().when(categoriasService).deletarCategoria(uuid);

    ResultActions response = mockMvc
        .perform(delete("/v1/categorias/{id}", uuid)
            .with(csrf()));

    response.andExpect(status().isOk());
  }
}