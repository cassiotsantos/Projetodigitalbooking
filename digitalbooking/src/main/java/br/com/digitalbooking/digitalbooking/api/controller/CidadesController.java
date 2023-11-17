package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.CidadesRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CidadesResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CidadesListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.CidadesWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.service.CidadesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cidades")
@Tag(name="Cidades")

public class CidadesController {

    private final CidadesService cidadesService;

    @Autowired
    public CidadesController(CidadesService cidadesService) {
        this.cidadesService = cidadesService;
    }

    //Buscar todos por termo
   /* @GetMapping
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
    }*/

    //Listar Todos
    @GetMapping
    public ResponseEntity<List<CidadesListResponse>> buscarTodasCidades() {
        List<Cidades> cidades = cidadesService.buscarTodasCidades();

        List<CidadesListResponse> response = cidades.stream()
                .map(cidade -> {
                    CidadesListResponse cidadesListResponse = new CidadesListResponse();
                    cidadesListResponse.setId(cidade.getId());
                    cidadesListResponse.setNome(cidade.getNome());
                    cidadesListResponse.setPais(cidade.getPais());
                    return cidadesListResponse;
                })
                .toList();

        return ResponseEntity.ok(response);
    }

    //método Criar
    @PostMapping
    ResponseEntity<UUID> criarCidades(@RequestBody @Valid CidadesRequest request) {

        Cidades cidades = new Cidades();
        cidades.setId(UUID.randomUUID());
        cidades.setNome(request.getNome());
        cidades.setPais(request.getPais());

        Cidades cidadesCriada = cidadesService.criarCidades(cidades);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadesCriada.getId());
    }

}
