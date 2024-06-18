package com.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter
public class PedidoDTO {

    @NotNull
    private Long numeroControle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @Min(1) @Max(10)
    private Long codigoCliente;
}
