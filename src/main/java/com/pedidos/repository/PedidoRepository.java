package com.pedidos.repository;

import com.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsByNumeroControle(Long numeroControle);
    List<Pedido> findByDataCadastro(LocalDate dataCadastro);
}