package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.CaracteristicasListResponse;
import br.com.digitalbooking.digitalbooking.api.dto.response.CaracteristicasResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CaracteristicasWrapperResponse {
    private List<CaracteristicasResponse> caracteristicas;

    public void setCaracteristicas(List<CaracteristicasListResponse> list) {
    }
}
