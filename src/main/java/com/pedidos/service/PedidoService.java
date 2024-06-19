package com.pedidos.service;

import com.pedidos.dto.PedidoDTO;
import com.pedidos.dto.PedidoSalvoDTO;
import com.pedidos.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {
    PedidoSalvoDTO salvarPedido(List<PedidoDTO> pedidoDTO);
    List<Pedido> listarPedidos(LocalDate dataCadastro, Long numeroControle);

}