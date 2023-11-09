package br.com.digitalbooking.digitalbooking.api.controller;


import br.com.digitalbooking.digitalbooking.api.dto.request.CaracteristicasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.*;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.service.CaracteristicasService;
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
@Tag(name = "Caracteristicas" )
public class CaracteristicasController {
  private final CaracteristicasService caracteristicasService;

  @Autowired
  public CaracteristicasController(CaracteristicasService caracteristicasService) {
    this.caracteristicasService = caracteristicasService;
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
  ResponseEntity<UUID> criarCaracteristicas(@RequestBody @Valid CaracteristicasRequest request) {

    Caracteristicas caracteristicas = new Caracteristicas();
    caracteristicas.setId(UUID.randomUUID());
    caracteristicas.setNome(request.getNome());
    caracteristicas.setIcone(request.getIcone());

    Caracteristicas caracteristicaCriada = caracteristicasService.criar(caracteristicas);
    return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaCriada.getId());
  }

  //Método atualizar
  @PutMapping("id")
  ResponseEntity<?>atualizarCaracteristicas(@PathVariable UUID id, @RequestBody @Valid CaracteristicasRequest request){

    Caracteristicas caracteristicas = caracteristicasService.buscarCaracteristicasPorId(id);
    caracteristicas.setNome(request.getNome());
    caracteristicas.setIcone(request.getIcone());

    Caracteristicas atualizarCaracteristica = caracteristicasService.atualizarCaracteristicas(id,caracteristicas);
    return ResponseEntity.ok(atualizarCaracteristica);


  }

  //Método deletar
  @DeleteMapping("{id}")
  ResponseEntity<Void> deletarCaracteristicas(@PathVariable UUID id){
    caracteristicasService.deletarCaracteristicas(id);
    return ResponseEntity.ok().build();
  }
}