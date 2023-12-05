package br.com.digitalbooking.digitalbooking.api.dto.response;


import br.com.digitalbooking.digitalbooking.domain.entity.StatusResersas;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter

public class ReservasResponse {

    private UUID id;
    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusResersas status;
}
