package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Cidades;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ProdutosListResponse {
    private UUID id;
    private String nome;
    private String descricao;
    private UUID categoriasId;
    private UUID cidadesId;
    private List<UUID> imagensId;
    private Set<UUID> caracteristicasProdutoId;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;
}
