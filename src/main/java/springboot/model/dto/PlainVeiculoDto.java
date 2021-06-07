package springboot.model.dto;

import lombok.Data;
import springboot.model.Veiculo;

@Data
public class PlainVeiculoDto {
    
    private long id;
    private String marca;
    private String modelo;
    private int ano;
    private float valor;

    
    private int diaRodizio;
    private boolean rodizioAtivo;
    
    public static PlainVeiculoDto from(Veiculo veiculo){
        PlainVeiculoDto plainVeiculoDto = new PlainVeiculoDto();

        plainVeiculoDto.setId(veiculo.getId());
        plainVeiculoDto.setMarca(veiculo.getMarca());
        plainVeiculoDto.setModelo(veiculo.getModelo());
        plainVeiculoDto.setAno(veiculo.getAno());
        plainVeiculoDto.setValor(veiculo.getValor());
        plainVeiculoDto.setDiaRodizio(veiculo.getDiaRodizio());
        plainVeiculoDto.setRodizioAtivo(veiculo.isRodizioAtivo());

        return plainVeiculoDto;
    }
}
