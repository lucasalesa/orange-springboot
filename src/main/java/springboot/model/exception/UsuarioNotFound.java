package springboot.model.exception;


public class UsuarioNotFound extends RuntimeException{
    
    public UsuarioNotFound(long id){
        super("Usuario de id " + id +" não encontrado");
    }
}
