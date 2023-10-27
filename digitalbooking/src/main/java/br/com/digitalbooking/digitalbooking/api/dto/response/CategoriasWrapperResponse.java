package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CategoriasWrapperResponse {

    private List<CategoriasListResponse> categorias;
}
