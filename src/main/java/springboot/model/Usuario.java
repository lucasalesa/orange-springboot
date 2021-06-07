package springboot.model;

import java.util.Date;
import java.util.List;
// import java.util.Objects;
// import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import springboot.model.dto.UsuarioDto;

@Data
@Entity
@Table
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private Date data_nascimento;

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Veiculo> veiculos;

    
    public static Usuario from(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setData_nascimento(usuarioDto.getData_nascimento());

        return usuario;
    }
    

}
