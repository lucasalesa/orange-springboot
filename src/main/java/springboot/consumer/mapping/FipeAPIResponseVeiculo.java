package springboot.consumer.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FipeAPIResponseVeiculo {
    
    @JsonProperty("Valor")
    private String valor;
    
    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private int ano;
    
}
