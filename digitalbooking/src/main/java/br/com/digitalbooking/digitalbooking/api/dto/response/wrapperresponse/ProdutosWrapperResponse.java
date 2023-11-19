package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ProdutosListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProdutosWrapperResponse {

    private List<ProdutosListResponse> produtos;

}
