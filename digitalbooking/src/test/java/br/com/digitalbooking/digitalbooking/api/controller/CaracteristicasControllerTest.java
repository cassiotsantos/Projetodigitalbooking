package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CaracteristicasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CaracteristicasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.service.impl.CaracteristicasServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  CaracteristicasServiceImpl service;


  @BeforeEach
  void setUp() {
    var caracteristica1 = new Caracteristicas(
        UUID.randomUUID(),
        "nome1",
        "icone1"
    );
    var caracteristica2 = new Caracteristicas(
        UUID.randomUUID(),
        "nome2",
        "icone2"
    );
    var caracteristica3 = new Caracteristicas(
        UUID.randomUUID(),
        "nome3",
        "icone3"
    );
    var listCaracteristicas = List.of(
        caracteristica1,
        caracteristica2,
        caracteristica3
    );
    var caracteristicaResponse1 = new CaracteristicasResponse(
        caracteristica1.getId(),
        caracteristica1.getNome(),
        caracteristica1.getIcone()
    );
    var caracteristicaResponse2 = new CaracteristicasResponse(
        caracteristica2.getId(),
        caracteristica2.getNome(),
        caracteristica2.getIcone()
    );
    var caracteristicaResponse3 = new CaracteristicasResponse(
        caracteristica3.getId(),
        caracteristica3.getNome(),
        caracteristica3.getIcone()
    );
    var listCaracteristicasResponse = new CaracteristicasWrapperResponse(
        List.of(new CaracteristicasListResponse(
                caracteristica1.getId(),
                caracteristica1.getNome(),
                caracteristica1.getIcone()
            ),
            new CaracteristicasListResponse(
                caracteristica2.getId(),
                caracteristica2.getNome(),
                caracteristica2.getIcone()
            ),
            new CaracteristicasListResponse(
                caracteristica3.getId(),
                caracteristica3.getNome(),
                caracteristica3.getIcone()
            )
        )
    );
  }

  @Test
  void buscarCaracteristicasPorId() throws Exception {

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
  void buscarCaracteristicas() throws Exception {
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
  void criarCaracteristicas() throws Exception {
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
  void atualizarCaracteristicas() {
  }

  @Test
  void deletarCaracteristicas() {
  }
}