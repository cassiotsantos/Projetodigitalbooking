package br.com.digitalbooking.digitalbooking.api.controller;


import br.com.digitalbooking.digitalbooking.api.dto.request.CaracteristicasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CaracteristicasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CaracteristicasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.service.CaracteristicasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/caracteristicas")
@Tag(name = "Caracteristicas")
public class CaracteristicasController {
  private final CaracteristicasService caracteristicasService;
  private final ObjectMapper objectMapper;

  @Autowired
  public CaracteristicasController(CaracteristicasService caracteristicasService, ObjectMapper objectMapper) {
    this.caracteristicasService = caracteristicasService;
    this.objectMapper = objectMapper;
  }

  //Buscar por ID
  @GetMapping("{id}")
  ResponseEntity<CaracteristicasResponse> buscarCaracteristicasPorId(@PathVariable UUID id) {
    Caracteristicas caracteristicas = caracteristicasService.buscarCaracteristicasPorId(id);
    CaracteristicasResponse response = caracteristicasResponseByCaracteristicas(caracteristicas);
    return ResponseEntity.ok().body(response);
  }

  private CaracteristicasResponse caracteristicasResponseByCaracteristicas(Caracteristicas caracteristicas) {
    CaracteristicasResponse caracteristicasResponse = new CaracteristicasResponse();
    caracteristicasResponse.setId(caracteristicas.getId());
    caracteristicasResponse.setNome(caracteristicas.getNome());
    caracteristicasResponse.setIcone(caracteristicas.getIcone());

    return caracteristicasResponse;

  }

  //Buscar todos por termo
  @GetMapping
  ResponseEntity<CaracteristicasWrapperResponse> buscarCaracteristicas(@RequestParam String termo) {
    List<Caracteristicas> caracteristicas = caracteristicasService.buscarCaracteristicas(termo);
    CaracteristicasWrapperResponse caracteristicasWrapperResponse = new CaracteristicasWrapperResponse();
    caracteristicasWrapperResponse.setCaracteristicas(
        caracteristicas.stream()
            .map(caracteristica -> {
              CaracteristicasListResponse caracteristicasListResponse = new CaracteristicasListResponse();
              caracteristicasListResponse.setId(caracteristica.getId());
              caracteristicasListResponse.setNome(caracteristica.getNome());
              caracteristicasListResponse.setIcone(caracteristica.getIcone());
              return caracteristicasListResponse;
            }).toList());
    return ResponseEntity.ok(caracteristicasWrapperResponse);

  }

  //método Criar
  @PostMapping
  ResponseEntity<CaracteristicasResponse> criarCaracteristicas(@RequestBody @Valid CaracteristicasRequest request) {

    Caracteristicas caracteristicas = objectMapper.convertValue(request, Caracteristicas.class);
    Caracteristicas caracteristicasCriado = caracteristicasService.criarCaracteristicas(caracteristicas);
    CaracteristicasResponse caracteristicasResponse = objectMapper.convertValue(caracteristicas, CaracteristicasResponse.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicasResponse);

/*
    Caracteristicas caracteristicas = new Caracteristicas();
    caracteristicas.setId(UUID.randomUUID());
    caracteristicas.setNome(request.getNome());
    caracteristicas.setIcone(request.getIcone());

    Caracteristicas caracteristicaCriada = caracteristicasService.criar(caracteristicas);
    return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaCriada.getId());
  */
  }

  //Método atualizar
  @PutMapping("{id}")
  ResponseEntity<?> atualizarCaracteristicas(@PathVariable UUID id, @RequestBody @Valid CaracteristicasRequest request) {

    Caracteristicas caracteristicas = caracteristicasService.buscarCaracteristicasPorId(id);
    caracteristicas.setNome(request.getNome());
    caracteristicas.setIcone(request.getIcone());

    Caracteristicas atualizarCaracteristica = caracteristicasService.atualizarCaracteristicas(id, caracteristicas);
    return ResponseEntity.ok(atualizarCaracteristica);
  }

  //Método deletar
  @DeleteMapping("{id}")
  ResponseEntity<Void> deletarCaracteristicas(@PathVariable UUID id) {
    caracteristicasService.deletarCaracteristicas(id);
    return ResponseEntity.ok().build();
  }
}