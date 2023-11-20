package br.com.digitalbooking.digitalbooking.api.dto.response.wrapperresponse;

import br.com.digitalbooking.digitalbooking.api.dto.response.listresponse.ImagensListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ImagensWrapperResponse {
    private List<ImagensListResponse> imagens;
}
