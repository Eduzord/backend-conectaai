package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.UsuarioDtoRequest;
import br.senac.ConectaAi.dto.request.UsuarioDtoUpdateRequest;
import br.senac.ConectaAi.dto.response.UsuarioDtoResponse;
import br.senac.ConectaAi.entity.Usuario;
import br.senac.ConectaAi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return this.usuarioRepository.listarUsuariosAtivos();
    }

    public Usuario listarUsuarioPorId(Integer idUsuario){
        return this.usuarioRepository.obterUsuarioAtivoPorId(idUsuario);
    }

    public UsuarioDtoResponse salvar(UsuarioDtoRequest usuarioDtoRequest){
        Usuario usuario = modelMapper.map(usuarioDtoRequest, Usuario.class);
        usuario.setDataCadastro(LocalDateTime.now());
        //TODO criptografar a senha assim que o SpringSecurity for aplicado
        usuario.setStatus(1);

        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);

        return modelMapper.map(usuarioSalvo, UsuarioDtoResponse.class);
    }

    public UsuarioDtoResponse atualizar(Integer idUsuario, UsuarioDtoUpdateRequest usuarioDtoUpdateRequest){
        Usuario usuario = this.listarUsuarioPorId(idUsuario);
        if(usuario != null){
            modelMapper.map(usuarioDtoUpdateRequest, usuario);
            Usuario usuarioTemp = this.usuarioRepository.save(usuario);

            return modelMapper.map(usuarioTemp,UsuarioDtoResponse.class);
        }else{
            return null;
        }

    }

    public void apagar(Integer idUsuario){
        Usuario usuario = listarUsuarioPorId(idUsuario);
        if(usuario != null){
            this.usuarioRepository.apagadorLogico(idUsuario);
        }
    }



}
