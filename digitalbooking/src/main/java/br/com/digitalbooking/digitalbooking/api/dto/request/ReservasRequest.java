package br.com.digitalbooking.digitalbooking.api.dto.request;

import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter

public class ReservasRequest {

    @NotBlank
    private UUID id;
    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusReservas status;
}
