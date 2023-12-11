package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CaracteristicasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CaracteristicasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.service.JwtService;
import br.com.digitalbooking.digitalbooking.domain.service.UserService;
import br.com.digitalbooking.digitalbooking.domain.service.impl.CaracteristicasServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CaracteristicasController.class)
class CaracteristicasControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  JwtService jwtService;

  @MockBean
  UserService userService;

  @MockBean
  CaracteristicasServiceImpl service;

  @Test
  @WithMockUser
  void shouldGetByIdCaracteristicasSuccessfully() throws Exception {

    var caracteristica1 = new Caracteristicas(
        UUID.randomUUID(),
        "nome1",
        "icone1"
    );

    var caracteristicaResponse1 = new CaracteristicasResponse(
        caracteristica1.getId(),
        caracteristica1.getNome(),
        caracteristica1.getIcone()
    );

    given(service.buscarCaracteristicasPorId(caracteristica1.getId()))
        .willReturn(caracteristica1);

    var response = mockMvc
        .perform(
            get("/v1/caracteristicas/{id}", caracteristica1.getId()));

    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id")
            .value(caracteristicaResponse1.getId().toString()))
        .andExpect(jsonPath("$.nome")
            .value(caracteristicaResponse1.getNome()))
        .andExpect(jsonPath("$.icone")
            .value(caracteristicaResponse1.getIcone()));
  }

  @Test
  @WithMockUser
  void shouldGetListCaracteristicasSuccessfully() throws Exception {
    var wrapper = new CaracteristicasWrapperResponse(
        List.of(
            new CaracteristicasListResponse(
                UUID.randomUUID(),
                "nome1",
                "icone1"
            ),
            new CaracteristicasListResponse(
                UUID.randomUUID(),
                "nome2",
                "icone2"
            ),
            new CaracteristicasListResponse(
                UUID.randomUUID(),
                "nome3",
                "icone3"
            )
        )
    );

    given(service.buscarCaracteristicas("nome"))
        .willReturn(List.of(
                new Caracteristicas(
                    wrapper
                        .getCaracteristicas().get(0).getId(),
                    wrapper
                        .getCaracteristicas().get(0).getNome().toString(),
                    wrapper
                        .getCaracteristicas().get(0).getIcone().toString()
                ),
                new Caracteristicas(
                    wrapper
                        .getCaracteristicas().get(1).getId(),
                    wrapper
                        .getCaracteristicas().get(1).getNome().toString(),
                    wrapper
                        .getCaracteristicas().get(1).getIcone().toString()
                ),
                new Caracteristicas(
                    wrapper
                        .getCaracteristicas().get(2).getId(),
                    wrapper
                        .getCaracteristicas().get(2).getNome().toString(),
                    wrapper
                        .getCaracteristicas().get(2).getIcone().toString()
                )
            )
        );

    var response = mockMvc
        .perform(
            get("/v1/caracteristicas?termo=nome"));

    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.caracteristicas[0].id")
            .value(wrapper.getCaracteristicas().get(0).getId().toString()))
        .andExpect(jsonPath("$.caracteristicas[0].nome")
            .value(wrapper.getCaracteristicas().get(0).getNome()))
        .andExpect(jsonPath("$.caracteristicas[0].icone")
            .value(wrapper.getCaracteristicas().get(0).getIcone()))
        .andExpect(jsonPath("$.caracteristicas[1].id")
            .value(wrapper.getCaracteristicas().get(1).getId().toString()))
        .andExpect(jsonPath("$.caracteristicas[1].nome")
            .value(wrapper.getCaracteristicas().get(1).getNome()))
        .andExpect(jsonPath("$.caracteristicas[1].icone")
            .value(wrapper.getCaracteristicas().get(1).getIcone()))
        .andExpect(jsonPath("$.caracteristicas[2].id")
            .value(wrapper.getCaracteristicas().get(2).getId().toString()))
        .andExpect(jsonPath("$.caracteristicas[2].nome")
            .value(wrapper.getCaracteristicas().get(2).getNome()))
        .andExpect(jsonPath("$.caracteristicas[2].icone")
            .value(wrapper.getCaracteristicas().get(2).getIcone()));
  }

  @Test
  @WithMockUser
  void shouldCreateCaracteristicasSuccessfully() throws Exception {
    var caracteristica1 = new Caracteristicas(
        UUID.randomUUID(),
        "nome1",
        "icone1"
    );
    var caracteristicaResponse1 = new CaracteristicasResponse(
        caracteristica1.getId(),
        caracteristica1.getNome().toString(),
        caracteristica1.getIcone().toString()
    );

    var caracteristicaRequest1 = new CaracteristicasResponse(
        caracteristica1.getId(),
        caracteristica1.getNome().toString(),
        caracteristica1.getIcone().toString()
    );
    given(service.criarCaracteristicas(caracteristica1))
        .willReturn(caracteristica1);

    var response = mockMvc
        .perform(
            post("/v1/caracteristicas")
                .with(csrf())
                .contentType("application/json")
                .content(mapper.writeValueAsString(caracteristicaRequest1))
        );

    response
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.nome")
            .value(caracteristicaResponse1.getNome()))
        .andExpect(jsonPath("$.icone")
            .value(caracteristicaResponse1.getIcone()));


  }

  @Test
  @WithMockUser
  void shouldUpdateCaracteristicasSuccessfully() throws Exception {

    var caracteristicaToUpdate = new Caracteristicas(UUID.randomUUID(), "nome1", "icone1");

    var newCaracteristica = new Caracteristicas(UUID.randomUUID(), "nome2", "icone2");

    given(service.buscarCaracteristicasPorId(caracteristicaToUpdate.getId()))
        .willReturn(caracteristicaToUpdate);
    given(service.atualizarCaracteristicas(caracteristicaToUpdate.getId(), caracteristicaToUpdate))
        .willReturn(newCaracteristica);

    var response = mockMvc
        .perform(put("/v1/caracteristicas/{id}", caracteristicaToUpdate.getId())
            .with(csrf())
            .contentType("application/json")
            .content(mapper.writeValueAsString(newCaracteristica)));

    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(newCaracteristica.getId().toString()))
        .andExpect(jsonPath("$.nome").value(newCaracteristica.getNome()))
        .andExpect(jsonPath("$.icone").value(newCaracteristica.getIcone()));

  }

  @Test
  void shouldDeleteCaracteristicasSuccessfully() {
  }
}