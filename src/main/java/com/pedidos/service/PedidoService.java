package com.pedidos.service;

import com.pedidos.dto.PedidoDTO;
import com.pedidos.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {
    void salvarPedido(List<PedidoDTO> pedidoDTO);
    List<Pedido> listarPedidos();
    List<Pedido> listarPedidosPorData(LocalDate dataCadastro);
}