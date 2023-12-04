package br.com.digitalbooking.digitalbooking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class SingIn {
    private String email;
    private String senha;
}
