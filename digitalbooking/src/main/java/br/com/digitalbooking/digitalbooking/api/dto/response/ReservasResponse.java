package br.com.digitalbooking.digitalbooking.api.dto.response;


import br.com.digitalbooking.digitalbooking.api.dto.request.ReservasRequest;
import br.com.digitalbooking.digitalbooking.domain.entity.StatusReservas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ReservasResponse {

    private UUID id;
    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusReservas status;
    private ProdutosResponse produtosResponse;
    private UsuariosResponse usuariosResponse;
    private CidadesResponse cidade;
    
    public ReservasResponse () {
        
    }


}
