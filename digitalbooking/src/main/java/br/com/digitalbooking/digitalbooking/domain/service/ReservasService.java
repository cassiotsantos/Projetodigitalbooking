package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ReservasService {
    Reservas criarReserva (Reservas reservas);
    List<Reservas> findByProdutosId (UUID produtoId);
    List<Reservas> findByUsuario(UUID usuarioId);

}
