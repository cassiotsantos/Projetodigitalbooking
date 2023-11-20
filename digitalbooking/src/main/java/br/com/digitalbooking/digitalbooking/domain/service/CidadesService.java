package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;

import java.util.List;
import java.util.UUID;

public interface CidadesService {
    Cidades criarCidades (Cidades cidades);
   List<Cidades> buscarCidades (String termo);
   Cidades buscarCidadePorId (UUID id);
}
