package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;

import java.util.List;
public interface CidadesService {
    Cidades criarCidades (Cidades cidades);
    List<Cidades> buscarCidades (String termo);
    List<Cidades> buscarTodasCidades();
}
