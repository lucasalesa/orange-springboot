package springboot.model.exception;


public class VeiculoInvalido extends RuntimeException{
    
    public VeiculoInvalido(){
        super("Veiculo inválido");
    }
}
