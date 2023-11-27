package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.CidadesRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CidadesResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.ImagensResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CidadesListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CidadesWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import br.com.digitalbooking.digitalbooking.domain.service.CidadesService;
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
@RequestMapping("v1/cidades")
@Tag(name="Cidades")

public class CidadesController {

    private final CidadesService cidadesService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CidadesController(CidadesService cidadesService, ObjectMapper objectMapper) {
        this.cidadesService = cidadesService;
        this.objectMapper = objectMapper;
    }

   //Buscar todos por termo
  @GetMapping
    ResponseEntity<CidadesWrapperResponse> buscarCidades(@RequestParam String termo) {
        List<Cidades> cidades = cidadesService.buscarCidades(termo);
        CidadesWrapperResponse cidadesWrapperResponse = new CidadesWrapperResponse();
        cidadesWrapperResponse.setCidades(
                cidades.stream()
                        .map(cidade -> {
                            CidadesListResponse cidadesListResponse = new CidadesListResponse();
                            cidadesListResponse.setId(cidade.getId());
                            cidadesListResponse.setNome(cidade.getNome());
                            cidadesListResponse.setPais(cidade.getPais());
                            return cidadesListResponse;
                        }).toList());
        return ResponseEntity.ok(cidadesWrapperResponse);
    }


    //método Criar
    @PostMapping
    ResponseEntity<CidadesResponse> criarCidades(@RequestBody @Valid CidadesRequest request) {

        Cidades cidades = objectMapper.convertValue(request, Cidades.class);
        Cidades cidadeCriada = cidadesService.criarCidades(cidades);
        CidadesResponse cidadesResponse = objectMapper.convertValue(cidades, CidadesResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadesResponse);
    }

    @GetMapping("{id}")
    ResponseEntity<CidadesResponse> buscarCidadePorId (@PathVariable UUID id) {

        Cidades cidades = cidadesService.buscarCidadePorId(id);
        CidadesResponse response = cidadeResponseBycidade(cidades);
        return ResponseEntity.ok(response);
    };


    private CidadesResponse cidadeResponseBycidade(Cidades cidades) {
        CidadesResponse cidadesResponse = new CidadesResponse();
        cidadesResponse.setId(cidades.getId());
        cidadesResponse.setNome(cidades.getNome());
        cidadesResponse.setPais(cidades.getPais());

       return cidadesResponse;
    }






}
