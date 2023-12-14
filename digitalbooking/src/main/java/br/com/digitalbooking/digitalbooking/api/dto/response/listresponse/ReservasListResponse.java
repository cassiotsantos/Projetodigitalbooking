package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ReservasListResponse {
    private UUID id;
    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private String status;
    private UUID produtosId;
    private UUID usuariosId;


}
