package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.service.CategoriasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/clinicas")
@Tag(name = "Categorias")
public class CategoriasController {
    private final CategoriasService categoriasService;

    public CategoriasController(CategoriasService categoriasService) {
        this.categoriasService = categoriasService;
    }

    @PostMapping
    ResponseEntity<Categorias> criarCategoria(@RequestBody @Valid Categorias categorias){
        return ResponseEntity.ok(categoriasService.criarCategoria(categorias));
    }



    @DeleteMapping("{id}")
    ResponseEntity<Void> deletarCategoria(@PathVariable UUID id){
        categoriasService.deletarCategoria(id);
        return ResponseEntity.ok().build();
    }




}
