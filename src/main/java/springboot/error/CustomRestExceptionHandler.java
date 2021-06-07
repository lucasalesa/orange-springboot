package springboot.error;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import springboot.model.exception.UsuarioNotFound;
import springboot.model.exception.VeiculoInvalido;



@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{
    


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                                        HttpHeaders headers, 
                                                        HttpStatus status, 
                                                        WebRequest request) {
        return new ResponseEntity<>("Entrada inválida", status);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        return new ResponseEntity<>("E-mail ou CPF já cadastrados", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsuarioNotFound.class})
    protected ResponseEntity<Object> handleUsuarioNotFound(UsuarioNotFound ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler({VeiculoInvalido.class})
    protected ResponseEntity<Object> handleVeiculoInvalido(VeiculoInvalido ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

}
