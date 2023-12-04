package br.com.digitalbooking.digitalbooking.api.dto.request;

import java.util.UUID;

public record RegisterDTO(UUID id, String nome, String sobrenome, String email, String senha, String funcoes, String funcoesUser) {
}
