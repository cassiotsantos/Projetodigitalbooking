package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.response.CategoriasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.request.CategoriasRequest;
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
@RequestMapping("categorias")
@Tag(name = "Categorias")

public class CategoriasController {
    private final CategoriasService categoriasService;
    private final ObjectMapper objectMapper;


    @Autowired
    public CategoriasController(CategoriasService categoriasService, ObjectMapper objectMapper) {

        this.categoriasService = categoriasService;
        this.objectMapper = objectMapper;
    };

 /*   @PostMapping
    ResponseEntity<?> criarCategoria(@RequestBody @Valid List<CategoriasRequest> request){

        List<Categorias> listaCategorias = new ArrayList<>();

        for (CategoriasRequest requestList : request) {
            Categorias categorias = objectMapper.convertValue(requestList, Categorias.class);
            listaCategorias.add(categorias);
        }
        return ResponseEntity.ok(categoriasService.criarCategoria(listaCategorias));
    }*/


    @PostMapping
    ResponseEntity<?> criarCategoria(@RequestBody @Valid CategoriasRequest request) {

        Categorias categorias = new Categorias();
        categorias.setId(request.getId());
        categorias.setNome(request.getNome());
        categorias.setUrlImage(request.getUrlImage());
        categorias.setDescricao(request.getDescricao());

        Categorias categoriaCriada = categoriasService.criarCategoria(categorias);
        return ResponseEntity.ok(categoriaCriada.getId());

    }



    @GetMapping("{id}")
    ResponseEntity<CategoriasResponse> buscarCategoriasPorId(@PathVariable UUID id){
        Categorias categorias = categoriasService.buscarCategoriasPorId(id);
        CategoriasResponse response = categoriasResponseByCategoria(categorias);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    ResponseEntity<?>atualizarCategorias(@PathVariable UUID id,@RequestBody @Valid CategoriasRequest request){

        Categorias categorias = categoriasService.buscarCategoriasPorId(id);
        categorias.setId(categorias.getId());
        categorias.setNome(categorias.getNome());
        categorias.setUrlImage(categorias.getUrlImage());
        categorias.setDescricao(categorias.getDescricao());


        Categorias atualizarCategorias = categoriasService.atualizarCategoria(id, categorias);
        return ResponseEntity.ok(atualizarCategorias);
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
