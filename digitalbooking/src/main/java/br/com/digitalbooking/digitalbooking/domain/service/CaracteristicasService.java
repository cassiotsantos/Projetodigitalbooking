package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;

import java.util.List;
import java.util.UUID;

public interface CaracteristicasService {
    Caracteristicas criarCaracteristicas(Caracteristicas caracteristicas);

    List<Caracteristicas> buscarCaracteristicas(String termo);
    Caracteristicas buscarCaracteristicasPorId(UUID id);
    Caracteristicas atualizarCaracteristicas(UUID id, Caracteristicas caracteristicas);
    void deletarCaracteristicas(UUID id);
}
