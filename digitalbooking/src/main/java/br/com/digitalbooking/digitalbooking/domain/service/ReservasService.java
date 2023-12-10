package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.api.dto.response.ReservasResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;

import java.util.List;
import java.util.UUID;

public interface ReservasService {
    ReservasResponse criarReserva (Reservas request, UUID usuarioId, UUID produtos);
    List<Reservas> findByProdutosId (UUID produtoId);
    List<Reservas> findByUsuarioId(Usuarios usuarioId);

}
