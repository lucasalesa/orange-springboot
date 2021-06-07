package springboot.consumer.mapping;

import lombok.Data;

@Data
public class FipeAPIResponseObject {
    
    private FipeAPIResponse[] modelos;
    private FipeAPIResponse[] anos;
}
