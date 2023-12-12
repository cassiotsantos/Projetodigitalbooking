package br.com.digitalbooking.digitalbooking.api.dto.response.listresponse;

import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ReservasListResponse {

    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusReservas status;
    private UUID produtosId;
    private UUID usuariosId;


}
