package springboot.consumer;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import springboot.consumer.mapping.FipeAPIResponse;
import springboot.consumer.mapping.FipeAPIResponseObject;
import springboot.consumer.mapping.FipeAPIResponseVeiculo;
import springboot.model.Veiculo;
import springboot.model.exception.VeiculoInvalido;

public class FipeAPIConsumer {
    
    @Autowired
    private RestTemplate template;

    public Veiculo getValorVeiculo(Veiculo veiculo){

        String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";

        FipeAPIResponse[] marcas = template.getForObject(url, FipeAPIResponse[].class);

        FipeAPIResponse marca = Arrays.asList(marcas)
                .stream()
                .filter(m -> Objects.equals(m.getNome(), veiculo.getMarca()))
                .findFirst()
                .orElseThrow( () ->
                    new VeiculoInvalido()
                );
        
        url += "/" + marca.getCodigo() + "/modelos";

        FipeAPIResponseObject object = template.getForObject(url, FipeAPIResponseObject.class);

        FipeAPIResponse[] modelos = object.getModelos();

        FipeAPIResponse modelo = Arrays.asList(modelos)
                .stream()
                .filter(m -> Objects.equals(m.getNome(), veiculo.getModelo()))
                .findFirst()
                .orElseThrow(()->
                    new VeiculoInvalido()
                );

        url += "/" + modelo.getCodigo() + "/anos";

        FipeAPIResponse[] anos = template.getForObject(url, FipeAPIResponse[].class);


        FipeAPIResponse ano = Arrays.asList(anos)
                .stream()
                .filter(a -> Objects.equals(a.getNome().split(" ")[0], String.valueOf(veiculo.getAno())))
                .findFirst()
                .orElseThrow(()->
                    new VeiculoInvalido()
                );
        
        url += "/" + ano.getCodigo();

        FipeAPIResponseVeiculo veiculoResponse = template.getForObject(url, FipeAPIResponseVeiculo.class);
        
        return Veiculo.from(veiculoResponse);


    }

}
