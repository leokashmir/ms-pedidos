package com.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder @Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pedidosIncluidos",
        "mensagem",
        "numerosControle"
})
public class PedidoSalvoDTO {

    @JsonProperty("mensagem")
    private String mensagem;
    @JsonProperty("pedidos")
    private List<PedidoDTO> pedidos;
    @JsonProperty("pedidosIncluidos")
    private int pedidosIncluidos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
