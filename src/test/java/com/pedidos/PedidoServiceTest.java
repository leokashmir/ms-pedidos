package com.pedidos;


import com.pedidos.converter.PedidoConverter;
import com.pedidos.dto.PedidoDTO;
import com.pedidos.dto.PedidoSalvoDTO;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoConverter pedidoConverter;

    private static final String MSG_OK = "Todos os pedidos foram incluidos ";
    private static final String MSG_PARCIAL_ERRO = "Seguintes Pedidos já existem na base da dados ou não puderam ser incluidos";
    private static final String MSG_ERRO = "Nenhum dos Pedidos foram incluidos";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarPedido() {
        // Dados de entrada
        PedidoDTO pedidoDTO1 = PedidoDTO.builder()
                .numeroControle(155L)
                .dataCadastro(LocalDate.now())
                .nome("Produto 1")
                .valor(BigDecimal.valueOf(100))
                .quantidade(2)
                .codigoCliente(1L)
                .build();

        PedidoDTO pedidoDTO2 = PedidoDTO.builder()
                .numeroControle(255L)
                .dataCadastro(LocalDate.now())
                .nome("Produto 2")
                .valor(BigDecimal.valueOf(200))
                .quantidade(3)
                .codigoCliente(2L)
                .build();

        List<PedidoDTO> pedidosDto = Arrays.asList(pedidoDTO1, pedidoDTO2);

        // Dados simulados
        Pedido pedido1 = new Pedido(1L, 1L, LocalDate.now(), "Produto 1", BigDecimal.valueOf(100), 2, 1L, BigDecimal.valueOf(200));
        Pedido pedido2 = new Pedido(2L, 2L, LocalDate.now(), "Produto 2", BigDecimal.valueOf(200), 3, 2L, BigDecimal.valueOf(600));
        List<Pedido> pedidosSalvos = Arrays.asList(pedido1, pedido2);

        // Mockando comportamentos
        when(pedidoConverter.apply(any(PedidoDTO.class))).thenAnswer(invocation -> {
            PedidoDTO dto = invocation.getArgument(0);
            return Pedido.builder()
                    .numeroControle(dto.getNumeroControle())
                    .dataCadastro(dto.getDataCadastro())
                    .nome(dto.getNome())
                    .valor(dto.getValor())
                    .quantidade(dto.getQuantidade())
                    .codigoCliente(dto.getCodigoCliente())
                    .build();
        });
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido pedido = invocation.getArgument(0);
            pedido.setIdPedido(pedido.getNumeroControle()); // Simulando a geração do ID
            return pedido;
        });
        when(pedidoRepository.existsByNumeroControle(any(Long.class))).thenReturn(false);

        // Executando o método
        PedidoSalvoDTO resultado = pedidoService.salvarPedido(pedidosDto);

        // Verificando o resultado
        assertEquals(2, resultado.getPedidosIncluidos());
        assertEquals(MSG_OK, resultado.getMensagem());
    }
}
