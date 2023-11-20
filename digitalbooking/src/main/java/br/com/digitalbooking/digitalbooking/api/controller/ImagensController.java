package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.ImagensRequest;
import br.com.digitalbooking.digitalbooking.api.dto.request.ProdutosRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.ImagensResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ImagensListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.ImagensWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import br.com.digitalbooking.digitalbooking.domain.entity.Produtos;
import br.com.digitalbooking.digitalbooking.domain.service.ImagensService;
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
@RequestMapping ("v1/imagens")
@Tag(name = "Imagens")
public class ImagensController {

    private final ImagensService imagensService;    private final ObjectMapper objectMapper;


    @Autowired
    public ImagensController(ImagensService imagensService, ObjectMapper objectMapper) {
        this.imagensService = imagensService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<ImagensResponse> criarImagem(@RequestBody @Valid ImagensRequest request) {

        Imagens imagens = objectMapper.convertValue(request, Imagens.class);
        Imagens imagensCriado = imagensService.criarImagem(imagens);
        ImagensResponse imagensResponse = objectMapper.convertValue(imagens, ImagensResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(imagensResponse);
    }

    @GetMapping("{id}")
    ResponseEntity<ImagensResponse>buscarImagensPorId(@PathVariable UUID id) {

        Imagens imagens = imagensService.buscarImagensPorId(id);
        ImagensResponse imagensResponse = imagensResponseByImagens(imagens);
        return ResponseEntity.ok().body(imagensResponse);
    }

    @GetMapping
    ResponseEntity<ImagensWrapperResponse>buscarTodasImagens(){
        List<Imagens> imagens = imagensService.buscarTodasImagens();
        ImagensWrapperResponse imagensWrapperResponse = new ImagensWrapperResponse();

        imagensWrapperResponse.setImagens(imagens.stream().map( imagem ->{
            ImagensListResponse imagemResponse = new ImagensListResponse();

            imagemResponse.setId(imagem.getId());
            imagemResponse.setTitulo(imagem.getTitulo());
            imagemResponse.setUrl(imagem.getUrl());

            return imagemResponse;

        }).toList());

            return ResponseEntity.ok(imagensWrapperResponse);

    }

    private ImagensResponse imagensResponseByImagens (Imagens imagens){
        ImagensResponse imagensResponse = new ImagensResponse();
        imagensResponse.setId(imagens.getId());
        imagensResponse.setTitulo(imagens.getTitulo());
        imagensResponse.setUrl(imagens.getUrl());

        return imagensResponse;
    }

}
