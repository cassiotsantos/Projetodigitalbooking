package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.UsuariosListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuariosWrapperResponse {
    private List<UsuariosListResponse> usuarios;
}
