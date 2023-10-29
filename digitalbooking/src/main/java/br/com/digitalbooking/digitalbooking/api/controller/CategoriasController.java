package br.com.digitalbooking.digitalbooking.api.controller;


import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.service.CategoriasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categorias")
public class CategoriasController {

  private final CategoriasService categoriasService;

  @Autowired
  public CategoriasController(CategoriasService categoriasService) {
    this.categoriasService = categoriasService;
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
  ResponseEntity<UUID> criarCategorias(@RequestBody @Valid CategoriasRequest request) {

    Categorias categorias = new Categorias();
    categorias.setId(UUID.randomUUID());
    categorias.setNome(request.getNome());
    categorias.setUrlImage(request.getUrlImage());
    categorias.setDescricao(request.getDescricao());

    Categorias categoriaCriada = categoriasService.criar(categorias);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada.getId());
  }

}