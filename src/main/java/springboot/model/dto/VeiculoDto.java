package springboot.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import springboot.model.Veiculo;

@Data
public class VeiculoDto {
    
    private long id;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    private int ano;

    private float valor;
    
    private UsuarioDto usuario;


    private int diaRodizio;
    private boolean rodizioAtivo;
    


    public static VeiculoDto from(Veiculo veiculo){
        VeiculoDto veiculoDto = new VeiculoDto();

        veiculoDto.setId(veiculo.getId());
        veiculoDto.setMarca(veiculo.getMarca());
        veiculoDto.setModelo(veiculo.getModelo());
        veiculoDto.setAno(veiculo.getAno());
        veiculoDto.setValor(veiculo.getValor());
        veiculoDto.setDiaRodizio(veiculo.getDiaRodizio());
        veiculoDto.setRodizioAtivo(veiculo.isRodizioAtivo());
       
        veiculoDto.setUsuario(UsuarioDto.from(veiculo.getUsuario()));
        
        return veiculoDto;
    }
}
