package br.com.digitalbooking.digitalbooking.api.controller;


import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CategoriasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CategoriasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.service.CategoriasService;
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
@RequestMapping("v1/categorias")
@Tag(name = "Categorias")
public class CategoriasController {
  private final CategoriasService categoriasService;
  private final ObjectMapper objectMapper;

  @Autowired
  public CategoriasController(CategoriasService categoriasService, ObjectMapper objectMapper) {

    this.categoriasService = categoriasService;
    this.objectMapper = objectMapper;
  }

  //Buscar por ID

  @GetMapping("{id}")
  ResponseEntity<CategoriasResponse> buscarCategoriasPorId(@PathVariable UUID id) {
    Categorias categorias = categoriasService.buscarCategoriasPorId(id);
    CategoriasResponse response = categoriasResponseByCategorias(categorias);
    return ResponseEntity.ok().body(response);
  }

  private CategoriasResponse categoriasResponseByCategorias(Categorias categorias) {
    CategoriasResponse categoriasResponse = new CategoriasResponse();
    categoriasResponse.setId(categorias.getId());
    categoriasResponse.setNome(categorias.getNome());
    categoriasResponse.setUrlImage(categorias.getUrlImage());
    categoriasResponse.setDescricao(categorias.getDescricao());
    categoriasResponse.setQualificacao(categorias.getQualificacao());
    categoriasResponse.setCreatedAt(categorias.getCreatedAt());

    return categoriasResponse;

  }

  //Buscar todos por termo
  @GetMapping
  ResponseEntity<CategoriasWrapperResponse> buscarCategorias(@RequestParam String termo) {
    List<Categorias> categorias = categoriasService.buscarCategorias(termo);
    CategoriasWrapperResponse categoriasWrapperResponse = new CategoriasWrapperResponse();
    categoriasWrapperResponse.setCategorias(
        categorias.stream()
            .map(categoria -> {
              CategoriasListResponse categoriasListResponse = new CategoriasListResponse();
              categoriasListResponse.setId(categoria.getId());
              categoriasListResponse.setNome(categoria.getNome());
              categoriasListResponse.setUrlImage(categoria.getUrlImage());
              categoriasListResponse.setDescricao(categoria.getDescricao());
              categoriasListResponse.setQualificacao(categoria.getQualificacao());
              categoriasListResponse.setCreatedAt(categoria.getCreatedAt());
              return categoriasListResponse;
            }).toList());
    return ResponseEntity.ok(categoriasWrapperResponse);

  }

  //método Criar
  @PostMapping
  ResponseEntity<CategoriasResponse> criarCategorias(@RequestBody @Valid CategoriasRequest request) {

    Categorias categorias = objectMapper.convertValue(request, Categorias.class);
    Categorias categoriaCriada = categoriasService.criarCategorias(categorias);
    CategoriasResponse categoriasResponse = objectMapper.convertValue(categorias, CategoriasResponse.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriasResponse);
  }

  //Método atualizar
  @PutMapping("{id}")
  ResponseEntity<CategoriasResponse> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriasRequest request) {

    Categorias categorias = categoriasService.buscarCategoriasPorId(id);
    categorias.setNome(request.getNome());
    categorias.setDescricao(request.getDescricao());
    categorias.setUrlImage(request.getUrlImage());
    categorias.setQualificacao(request.getQualificacao());

    Categorias atualizarCategoria = categoriasService.atualizarCategoria(id, categorias);
    CategoriasResponse categoriasResponse = categoriasResponseByCategorias(atualizarCategoria);
    return ResponseEntity.ok(categoriasResponse);

  }

  //Método deletar
  @DeleteMapping("{id}")
  ResponseEntity<Void> deletarCategoria(@PathVariable UUID id) {
    categoriasService.deletarCategoria(id);
    return ResponseEntity.ok().build();
  }
}