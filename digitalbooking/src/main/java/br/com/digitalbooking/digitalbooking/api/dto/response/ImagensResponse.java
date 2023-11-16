package br.com.digitalbooking.digitalbooking.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class ImagensResponse {
    private UUID id;
    public String titulo;
    public String url;
}
