package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.service.CategoriasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping
    ResponseEntity<Categorias> criarCategoria(@RequestBody @Valid List<CategoriasRequest> request){

        List<Categorias> listaCategorias = new ArrayList<>();

        for (CategoriasRequest requestList : request) {
            Categorias categorias = objectMapper.convertValue(requestList, Categorias.class);
            listaCategorias.add(categorias);
        }
        return  ResponseEntity.ok(categoriasService.criarCategoria(listaCategorias));
    }



    @DeleteMapping("{id}")
    ResponseEntity<Void> deletarCategoria(@PathVariable UUID id){
        categoriasService.deletarCategoria(id);
        return ResponseEntity.ok().build();
    }


    private CategoriasResponse categoriasResponseByCategoria(Categorias categorias) {

        CategoriasResponse categoriasResponse = new CategoriasResponse();
        categoriasResponse.setId(categorias.getId());
        categoriasResponse.setNome(categorias.getNome());
        categoriasResponse.setUrlImage(categorias.getUrlImage());
        categoriasResponse.setDescricao(categorias.getDescricao());


        return categoriasResponse;
    }




}
