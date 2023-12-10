package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.ReservasResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.service.ReservasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/reservas")
@Tag(name = "Reservas")
public class ReservasController {

    private final ObjectMapper objectMapper;

    private final ReservasService reservasService;

    @Autowired

    public ReservasController(ObjectMapper objectMapper, ReservasService reservasService){
        this.objectMapper = objectMapper;
        this.reservasService = reservasService;
    }

    @PostMapping
    public ResponseEntity<ReservasResponse> criarReserva(@RequestBody @Valid ReservasRequest request){
        Reservas reservas = objectMapper.convertValue(request, Reservas.class);
        Reservas reservaCriada = reservasService.criarReserva(reservas, request.getUsuariosId(), request.getProdutosId());
        ReservasResponse reservasResponse = objectMapper.convertValue(reservaCriada, ReservasResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservasResponse);
    }

    //Inserir

    /*
    @PostMapping
    ResponseEntity<ReservasResponse> criarReservas(@RequestBody @Valid ReservasRequest request){
        Reservas reservas = objectMapper.convertValue(request,Reservas.class);
        Reservas reservaCriada = reservasService.criarReserva(reservas);
        ReservasResponse reservasResponse = objectMapper.convertValue(reservaCriada, ReservasResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservasResponse);
    }*/



}
