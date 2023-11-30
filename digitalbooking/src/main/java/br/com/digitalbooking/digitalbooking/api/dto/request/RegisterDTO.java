package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.FuncoesUser;

import java.util.UUID;

public record RegisterDTO(UUID id, String nome, String sobrenome, String email, String senha, String funcoes, String funcoesUser) {
}
