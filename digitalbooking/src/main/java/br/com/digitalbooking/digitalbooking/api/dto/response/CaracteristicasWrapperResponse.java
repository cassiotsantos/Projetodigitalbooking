package br.com.digitalbooking.digitalbooking.api.dto.response;

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
