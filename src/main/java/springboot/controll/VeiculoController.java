package springboot.controll;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.consumer.FipeAPIConsumer;
import springboot.model.Veiculo;
import springboot.model.dto.VeiculoDto;
import springboot.service.UsuarioService;
import springboot.service.VeiculoService;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    
    @Autowired
    private VeiculoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FipeAPIConsumer consumer;

    @PostMapping
    public ResponseEntity<VeiculoDto> insert(@RequestBody @Valid VeiculoDto veiculoDto, @RequestParam long usuario_id){
        Veiculo veiculo = Veiculo.from(veiculoDto);
        veiculo.setValor(consumer.getValorVeiculo(veiculo).getValor());

        veiculo.setUsuario(usuarioService.getById(usuario_id));

        Veiculo veiculoResponse = service.insert(veiculo);

        return new ResponseEntity<>(VeiculoDto.from(veiculoResponse), HttpStatus.OK);
    }
}
