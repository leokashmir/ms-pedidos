package com.pedidos.controller;

import com.pedidos.dto.PedidoDTO;
import com.pedidos.model.Pedido;
import com.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
@Validated
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(value="/json",consumes = "application/json")
    public ResponseEntity<List<Pedido>> criarPedidos(@RequestBody @Valid List<PedidoDTO> pedidosDTO) {
        if (isExcedeLimitePedidos(pedidosDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        pedidoService.salvarPedido(pedidosDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @PostMapping(value="/xml", consumes = "application/xml")
    public ResponseEntity<List<Pedido>> criarPedidosXml(@RequestBody @Valid List<PedidoDTO> pedidosDTO) {

        if (isExcedeLimitePedidos(pedidosDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        pedidoService.salvarPedido(pedidosDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos(
            @RequestParam(required = false) Long numeroControle,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCadastro) {

            return ResponseEntity.ok(pedidoService.listarPedidos(dataCadastro, numeroControle ));
    }

    private boolean isExcedeLimitePedidos(List<PedidoDTO> pedidos ){
        return pedidos.size() > 10;
    }
}