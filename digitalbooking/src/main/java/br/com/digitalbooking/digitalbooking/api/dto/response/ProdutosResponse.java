package br.com.digitalbooking.digitalbooking.api.dto.response;

import br.com.digitalbooking.digitalbooking.domain.entity.Caracteristicas;
import br.com.digitalbooking.digitalbooking.domain.entity.Categorias;
import br.com.digitalbooking.digitalbooking.domain.entity.Imagens;
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
    private List<Imagens> imagens;
    private CidadesResponse cidades;
    private CategoriasResponse categorias;
    private Set<Caracteristicas> caracteristicas;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;
}
