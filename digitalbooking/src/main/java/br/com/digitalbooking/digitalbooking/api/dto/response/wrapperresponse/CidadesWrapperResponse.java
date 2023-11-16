package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.CidadesResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CidadesListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CidadesWrapperResponse {
    private List<CidadesResponse> cidades;

    public void setCidades(List<CidadesListResponse> list){

    }

}
