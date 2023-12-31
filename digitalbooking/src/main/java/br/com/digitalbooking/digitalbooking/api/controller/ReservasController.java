package br.com.digitalbooking.digitalbooking.api.controller;

import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.ReservasResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ReservasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.ReservasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.exception.SearchException;
import br.com.digitalbooking.digitalbooking.domain.service.ReservasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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


    // Método Buscar Reserva por produto
    @GetMapping("/porproduto/{produto}")
    ResponseEntity<ReservasWrapperResponse> listaReservasPorProduto(@PathVariable UUID produto){
        List<Reservas> reservas = reservasService.findByProdutosId(produto);
        ReservasWrapperResponse reservasWrapperResponse = new ReservasWrapperResponse();
        reservasWrapperResponse.setReservas(
                reservas.stream()
                        .map(reserva -> {
                            ReservasListResponse reservasListResponse = new ReservasListResponse();
                            reservasListResponse.setId(reserva.getId());
                            reservasListResponse.setHoraInicio(reserva.getHoraInicio());
                            reservasListResponse.setDataInicio(reserva.getDataInicio());
                            reservasListResponse.setDataFinal(reserva.getDataFinal());
                            reservasListResponse.setStatus(reserva.getStatus().getStatus());
                            reservasListResponse.setProdutosId(reserva.getProdutos().getId());
                            reservasListResponse.setUsuariosId(reserva.getUsuarioId().getId());
                            return reservasListResponse;

                        }).toList());

        return ResponseEntity.ok(reservasWrapperResponse);
    }

    @GetMapping("/porusuario/{usuario}")
    ResponseEntity<ReservasWrapperResponse> listaReservasPorUsuario(@PathVariable UUID usuario){
        List<Reservas> reservas = reservasService.findByUsuarioIdId(usuario);
        ReservasWrapperResponse reservasWrapperResponse = new ReservasWrapperResponse();
        reservasWrapperResponse.setReservas(
                reservas.stream()
                        .map(reserva -> {
                            ReservasListResponse reservasListResponse = new ReservasListResponse();
                            reservasListResponse.setId(reserva.getId());
                            reservasListResponse.setHoraInicio(reserva.getHoraInicio());
                            reservasListResponse.setDataInicio(reserva.getDataInicio());
                            reservasListResponse.setDataFinal(reserva.getDataFinal());
                            reservasListResponse.setStatus(reserva.getStatus().getStatus());
                            reservasListResponse.setProdutosId(reserva.getProdutos().getId());
                            reservasListResponse.setUsuariosId(reserva.getUsuarioId().getId());
                            return reservasListResponse;
                        }).toList());
            return ResponseEntity.ok(reservasWrapperResponse);
        }

    //Método deletar
    @DeleteMapping("{id}")
    ResponseEntity<Void> eletarReserva(@PathVariable UUID id){
        reservasService.deletarReserva(id);
        return ResponseEntity.ok().build();
    }


//    @Operation(summary = "Listar resersas por produto ou usuário")
//    @GetMapping({"id"})
//    public ResponseEntity<List<ReservasWrapperResponse>> listarPorProdutos(@RequestParam(required = false) UUID usuarioId,
//                                                                           @RequestParam(required = false) UUID produtoId) {
//
//        if (produtoId != null){
//            return new ResponseEntity<>(reservasService.findByProdutosId(produtoId), HttpStatus.OK);
//        }
//        else if(usuarioId != null){
//            return new ResponseEntity<>(reservasService.findByUsuarioId(usuarioId), HttpStatus.OK);
//        }
//        else {
//            throw new SearchException("Não especificou a busca");
//        }
//
//    }

    }