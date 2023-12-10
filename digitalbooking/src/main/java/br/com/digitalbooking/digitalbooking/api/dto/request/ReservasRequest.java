package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter

public class ReservasRequest {

    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusReservas status;
    private UUID produtosId;
    private UUID usuariosId;

}
