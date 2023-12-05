package br.com.digitalbooking.digitalbooking.domain.service.impl;

import br.com.digitalbooking.digitalbooking.domain.entity.Reservas;
import br.com.digitalbooking.digitalbooking.domain.repository.ReservasRepository;
import br.com.digitalbooking.digitalbooking.domain.service.ReservasService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor

public class ReservasServiceImpl implements ReservasService {

    private final ReservasRepository reservasRepository;

    @Autowired
    public ReservasServiceImpl(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }


    @Override
    public Reservas criarReserva(Reservas reservas) {

        return reservasRepository.save(reservas);
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
