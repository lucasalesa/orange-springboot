package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.Veiculo;
import springboot.repository.VeiculoRepository;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repository;

    public Veiculo insert(Veiculo veiculo){
        return repository.save(veiculo);
    }
}
