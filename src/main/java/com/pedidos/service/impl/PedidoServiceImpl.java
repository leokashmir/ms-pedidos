package com.pedidos.service.impl;

import com.pedidos.converter.PedidoConverter;
import com.pedidos.dto.PedidoDTO;
import com.pedidos.dto.PedidoSalvoDTO;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoConverter pedidoConverter;
    private static final String MSG_PARCIAL_ERRO = "Seguintes Pedidos já existem na base da dados ou não puderam ser incluidos";
    private static final String MSG_ERRO = "Nenhum dos Pedidos foram incluidos";
    private static final String MSG_OK = "Todos os pedidos foram incluidos ";

    @Transactional
    @Override
    public PedidoSalvoDTO salvarPedido(List<PedidoDTO> listPedidosDto) {
      List<Pedido> listPedidosSalvos = new ArrayList<>();
      listPedidosDto.stream()
                .filter(p -> !isCadastrado(p))
                .forEach(pedidoDto -> {
                    Pedido pedido =  pedidoConverter.apply(pedidoDto);
                    calcularValorPedido(pedido);
                    listPedidosSalvos.add(pedidoRepository.save(pedido));

                });

        List<PedidoDTO> listPedidosRecusados = listPedidosDto.stream()
                .filter(dto -> listPedidosSalvos.stream()
                        .noneMatch(pedido -> Objects.equals(dto.getNumeroControle(), pedido.getNumeroControle())))
                .collect(Collectors.toList());



        return (listPedidosSalvos.isEmpty()) ?
                PedidoSalvoDTO.builder()
                        .mensagem(MSG_ERRO)
                        .build():
                (listPedidosRecusados.isEmpty()) ?
                PedidoSalvoDTO.builder()
                        .mensagem(MSG_OK)
                        .pedidosIncluidos(listPedidosSalvos.size())
                        .build():
                PedidoSalvoDTO.builder()
                        .pedidosIncluidos(listPedidosSalvos.size())
                        .mensagem(MSG_PARCIAL_ERRO)
                        .pedidos(listPedidosRecusados)
                        .build();
     }

    @Override
    public List<Pedido> listarPedidos(LocalDate dataCadastro, Long numeroControle) {
        List<Pedido> lstListaPedidos =  new ArrayList<>();

        if(dataCadastro != null){
            lstListaPedidos = pedidoRepository.findByDataCadastro(dataCadastro);
        }else if( numeroControle != null){
            lstListaPedidos.add(pedidoRepository.findByNumeroControle(numeroControle));
        }else{
            lstListaPedidos = pedidoRepository.findAll();
        }

        return   lstListaPedidos;
    }

    private boolean isCadastrado(PedidoDTO pedidoDto){
         return pedidoRepository.existsByNumeroControle(pedidoDto.getNumeroControle());
    }

    private void calcularValorPedido(Pedido pedido){
        BigDecimal valorTotal = pedido.getValor().multiply(new BigDecimal(pedido.getQuantidade()));
        valorTotal.setScale(2, RoundingMode.UP);

        if(pedido.getQuantidade() >= 10 ){
          pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.9)));
        }else if(pedido.getQuantidade() > 5){
           pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.95)));
        }else{
           pedido.setValorTotal(valorTotal);
        }
    }

}