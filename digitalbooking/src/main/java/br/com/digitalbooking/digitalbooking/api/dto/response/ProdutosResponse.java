package br.com.digitalbooking.digitalbooking.api.dto.response;

import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
public class ProdutosResponse {
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

    public ProdutosResponse(UUID id, String nome, String descricao, UUID categoriasId, UUID cidadesId, List<UUID> imagensId, Set<UUID> caracteristicasProdutoId, String latitude, String longitude, LocalDateTime createdAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriasId = categoriasId;
        this.cidadesId = cidadesId;
        this.imagensId = imagensId;
        this.caracteristicasProdutoId = caracteristicasProdutoId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }

    public ProdutosResponse() {

    }
}
