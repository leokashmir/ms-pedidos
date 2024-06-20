package com.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter @Builder
public class PedidoDTO {

    @NotNull(message = "O campo Numero Controle  é obrigatório ")
    private Long numeroControle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    @NotBlank (message = "O campo Nome  é obrigatório ")
    private String nome;

    @NotNull(message = "O campo Valor  é obrigatório ")
    @Positive
    private BigDecimal valor;

    @PositiveOrZero
    private Integer quantidade;

    @NotNull(message = "O campo Codigo do Cliente  é obrigatório ")
    @Min(value= 1, message="Cliente não Cadastrado"  )
    @Max(value= 10, message="Cliente não Cadastrado" )
    private Long codigoCliente;
}
