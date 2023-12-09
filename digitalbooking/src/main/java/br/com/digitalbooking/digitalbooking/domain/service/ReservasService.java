package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.api.dto.response.ReservasResponse;
import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.entity.Usuarios;

import java.util.List;
import java.util.UUID;

public interface ReservasService {
    ReservasResponse criarReserva (ReservasResponse reservas);
    List<Reservas> findByProdutosId (UUID produtoId);
    List<Reservas> findByUsuarioId(Usuarios usuarioId);

}
