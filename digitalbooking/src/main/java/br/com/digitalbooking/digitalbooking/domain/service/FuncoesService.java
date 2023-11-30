package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.Funcoes;

import java.util.List;
import java.util.UUID;

public interface FuncoesService {

    Funcoes criarFuncao (Funcoes funcoes);
    Funcoes buscarFuncaoPorId (UUID id);

    List <Funcoes> buscarTodasFuncoes();

}