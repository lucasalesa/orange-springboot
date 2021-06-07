package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.Usuario;
import springboot.model.exception.UsuarioNotFound;
import springboot.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario getById(long id){
        return repository.findById(id).orElseThrow( () ->
            new UsuarioNotFound(id)
        );
    }

    public Usuario insert(Usuario usuario){
        return repository.save(usuario);
    }

}
