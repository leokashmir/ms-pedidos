package com.pedidos.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "pedidos")
@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PEDIDOS")
    private Long idPedido;
    @Column(name="NUMERO_CONTROLE", unique = true, nullable = false)
    private Long numeroControle;
    @Column(name="DATA_CADASTRO")
    private LocalDate dataCadastro;
    @Column(name="NOME")
    private String nome;
    @Column(name="VALOR")
    private BigDecimal valor;
    @Column(name="QUANTIDADE")
    private Integer quantidade;
    @Column(name="COD_CLIENTE")
    private Long codigoCliente;
    @Column(name="VALOR_TOTAL")
    private BigDecimal valorTotal;

}
