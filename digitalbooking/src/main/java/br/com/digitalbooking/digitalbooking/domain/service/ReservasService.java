package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse.ReservasWrapperResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;

import java.util.List;
import java.util.UUID;

public interface ReservasService {
    Reservas criarReserva (Reservas request, UUID usuarioId, UUID produtos);
    List<Reservas> findByProdutosId (UUID produtoId);
    List<Reservas> findByUsuarioIdId(UUID usuarioId);
    void deletarReserva(UUID id);

}
