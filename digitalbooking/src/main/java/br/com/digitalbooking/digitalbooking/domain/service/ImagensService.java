package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;

import java.util.List;
import java.util.UUID;

public interface ImagensService {
    Imagens criarImagem (Imagens imagens);
    Imagens buscarImagensPorId (UUID id);
}
