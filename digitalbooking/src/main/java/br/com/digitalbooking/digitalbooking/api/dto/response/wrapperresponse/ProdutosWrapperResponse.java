package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.ProdutosResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ProdutosListResponse;

import java.util.List;

public class ProdutosWrapperResponse {

    private List<ProdutosResponse> produtos;

    public void setProdutos(List<ProdutosListResponse> list) {
    }
}
