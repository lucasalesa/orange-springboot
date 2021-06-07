package springboot.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import springboot.model.Usuario;

@Data
public class UsuarioDto {
    
    private long id;

    @NotBlank
    private String nome;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @CPF
    private String cpf;
    
    @NotNull
    private Date data_nascimento;

    private List<PlainVeiculoDto> veiculos;

    public static UsuarioDto from(Usuario usuario) {
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setData_nascimento(usuario.getData_nascimento());

        
        if(Objects.nonNull(usuario.getVeiculos()))
            usuarioDto.setVeiculos(usuario.getVeiculos().stream().map(PlainVeiculoDto::from).collect(Collectors.toList()));
        
        
        return usuarioDto;
    }
     
}
