package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.service.ReservasService;

import java.util.List;
import java.util.UUID;

public class ResersasServiceImpl implements ReservasService {
    @Override
    public Reservas criarReserva(Reservas reservas) {
        return null;
    }

    @Override
    public List<Reservas> findByProdutosId(UUID produtoId) {
        return null;
    }

    @Override
    public List<Reservas> findByUsuario(UUID usuarioId) {
        return null;
    }
}
