package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private final String jwt;
}
