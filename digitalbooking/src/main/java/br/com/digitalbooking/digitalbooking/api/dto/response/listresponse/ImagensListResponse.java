package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImagensListResponse {
    private UUID id;
    private String titulo;
    private String url;
}
