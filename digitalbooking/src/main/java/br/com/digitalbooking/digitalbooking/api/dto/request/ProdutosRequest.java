package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class ProdutosRequest {

    @NotBlank
    @Size(min =1)
    private String nome;
    @NotBlank
    @Size(min =10)
    private String descricao;
    @NotEmpty
    private CategoriasRequest categorias;
    @NotBlank
    private CidadesRequest cidades;
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
    private LocalDateTime createdAt;

}
