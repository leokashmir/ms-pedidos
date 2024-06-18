package com.pedidos.converter;

import com.pedidos.dto.PedidoDTO;
import com.pedidos.model.Pedido;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
public class PedidoConverter  implements Function<PedidoDTO, Pedido> {

    @Override
    public Pedido apply(PedidoDTO pedidoDTO) {

        return  Pedido.builder()
                .numeroControle(pedidoDTO.getNumeroControle())
                .dataCadastro(pedidoDTO.getDataCadastro() != null ? pedidoDTO.getDataCadastro() : LocalDate.now())
                .nome(pedidoDTO.getNome())
                .codigoCliente(pedidoDTO.getCodigoCliente())
                .valor(pedidoDTO.getValor())
                .quantidade(pedidoDTO.getQuantidade() != null ? pedidoDTO.getQuantidade() : 1)
                .build();
    }
}
