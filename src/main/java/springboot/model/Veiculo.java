package springboot.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import springboot.consumer.mapping.FipeAPIResponseVeiculo;
// import springboot.model.dto.VeiculoDto;
import springboot.model.dto.VeiculoDto;

@Entity
@Table
@Data
public class Veiculo{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String marca;
    private String modelo;
    private int ano;
    private float valor;

    @Transient
    private int diaRodizio;

    @Transient
    private boolean rodizioAtivo;

    @ManyToOne
    private Usuario usuario;



    public Veiculo(){

    }

    public static Veiculo from(VeiculoDto veiculoDto){

        Veiculo veiculo = new Veiculo();

        veiculo.setMarca(veiculoDto.getMarca());
        veiculo.setModelo(veiculoDto.getModelo());
        veiculo.setAno(veiculoDto.getAno());
        veiculo.setDiaRodizio(veiculoDto.getDiaRodizio());
        veiculo.setRodizioAtivo(veiculoDto.isRodizioAtivo());

        if(Objects.nonNull(veiculoDto.getUsuario()))
            veiculo.setUsuario(Usuario.from(veiculoDto.getUsuario()));

        return veiculo;

    }
    
    
    public static Veiculo from(FipeAPIResponseVeiculo fipeAPIVeiculo){
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(fipeAPIVeiculo.getMarca());
        veiculo.setModelo(fipeAPIVeiculo.getModelo());
        veiculo.setAno(fipeAPIVeiculo.getAno());

        String valor = fipeAPIVeiculo.getValor();
        valor = valor.split(" ")[1]
            .replace(".", "")
            .replace(",", ".");
        veiculo.setValor(Float.parseFloat(valor));
        return veiculo;
    }
    
    @PostLoad
    public void setRodizioInfo(){

        int[] diaRodizio = {2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        this.diaRodizio = diaRodizio[this.ano%10];

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.rodizioAtivo = this.diaRodizio == calendar.get(Calendar.DAY_OF_WEEK) ? true : false;

    }
    
}