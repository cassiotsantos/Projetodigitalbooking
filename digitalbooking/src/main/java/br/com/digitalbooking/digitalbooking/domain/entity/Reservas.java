package br.com.digitalbooking.digitalbooking.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalTime horaInicio;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private StatusReservas status;

    @ManyToOne
    @JoinColumn( name ="usuario_id", nullable = false,
            foreignKey =
            @ForeignKey(name = "fk_reservas_usuario")
    )
    private Usuarios usuarioId;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false,
            foreignKey =
            @ForeignKey(name = "fk_reservas_produto")
    )
    private Produtos produtos;

    public Reservas(){

    }

}
