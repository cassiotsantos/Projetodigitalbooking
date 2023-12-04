package br.com.digitalbooking.digitalbooking.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class SingUp {
    private String nome;
    private String email;
    private String senha;
    private TypeFuncoesUser role;
}
