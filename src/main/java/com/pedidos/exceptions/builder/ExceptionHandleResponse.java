package com.pedidos.exceptions.builder;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apiVersion",
        "error"
})
public class ExceptionHandleResponse {

    @JsonProperty("apiVersion")
    private String apiVersion;
    @JsonProperty("error")
    private ErrorHandle error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
