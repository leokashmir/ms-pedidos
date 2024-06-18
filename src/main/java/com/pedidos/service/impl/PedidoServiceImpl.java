package com.pedidos.service.impl;

import com.pedidos.converter.PedidoConverter;
import com.pedidos.dto.PedidoDTO;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoConverter pedidoConverter;

    @Transactional
    @Override
    public void salvarPedido(List<PedidoDTO> listPedidosDto) {

        listPedidosDto.stream()
                .filter(p -> !isCadastrado(p))
                .forEach(pedidoDto -> {
                    Pedido pedido =  pedidoConverter.apply(pedidoDto);
                    calcularValorPedido(pedido);
                    pedidoRepository.save(pedido);

                });
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> listarPedidosPorData(LocalDate dataCadastro) {
        return pedidoRepository.findByDataCadastro(dataCadastro);
    }

    private boolean isCadastrado(PedidoDTO pedidoDto){
         return pedidoRepository.existsByNumeroControle(pedidoDto.getNumeroControle());
    }

    private void calcularValorPedido(Pedido pedido){
        BigDecimal valorTotal = pedido.getValor().multiply(new BigDecimal(pedido.getQuantidade()));

        if(pedido.getQuantidade() >= 10 ){
          pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.9)));
        }else if(pedido.getQuantidade() > 5){
           pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.95)));
        }else{
           pedido.setValorTotal(valorTotal);
        }
    }

}