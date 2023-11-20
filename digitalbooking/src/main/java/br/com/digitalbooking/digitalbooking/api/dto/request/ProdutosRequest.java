package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
    private UUID categoriasId;
    private UUID cidadesId;
    private List<UUID> imagensId;
    private Set<UUID> caracteristicasProdutoId;
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
    private LocalDateTime createdAt;

}
