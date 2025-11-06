package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.FavoritoDtoRequest;
import br.senac.ConectaAi.dto.request.FavoritoDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.FavoritoDtoResponse;
import br.senac.ConectaAi.entity.Favorito;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.entity.Usuario;
import br.senac.ConectaAi.repository.FavoritoRepository;
import br.senac.ConectaAi.repository.FerramentaRepository;
import br.senac.ConectaAi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FerramentaRepository ferramentaRepository;



    public List<Favorito> listarFavoritos(){
        return this.favoritoRepository.listarFavoritosAtivos();
    }

    public Favorito listarFavoritoPorId(int idFavorito){
        return this.favoritoRepository.obterFavoritoAtivoPorId(idFavorito);
    }

    public FavoritoDtoResponse salvar(FavoritoDtoRequest favoritoDtoRequest){
        Integer usuarioId = favoritoDtoRequest.getIdUsuario();
        Integer ferramentaId = favoritoDtoRequest.getIdFerramenta();

        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId é obrigatório");
        }
        if (ferramentaId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ferramentaId é obrigatório");
        }

        Usuario usuario = usuarioRepository.obterUsuarioAtivoPorId(usuarioId);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ou inativo: " + usuarioId);
        }

        Ferramenta ferramenta = ferramentaRepository.obterFerramentaAtivaPorId(ferramentaId);
        if (ferramenta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada ou inativa: " + ferramentaId);
        }

        Favorito favorito = new Favorito();
        favorito.setData(LocalDateTime.now());
        favorito.setUsuario(usuario);
        favorito.setFerramenta(ferramenta);
        favorito.setStatus(1);

        Favorito favoritoSalvo = this.favoritoRepository.save(favorito);

        return modelMapper.map(favoritoSalvo, FavoritoDtoResponse.class);
    }

    public FavoritoDtoResponse atualizar(Integer idFavorito, FavoritoDtoRequestUpdate favoritoDtoRequestUpdate){
        Favorito favorito = this.listarFavoritoPorId(idFavorito);
        if(favorito != null){
            Integer usuarioId = favoritoDtoRequestUpdate.getIdUsuario();
            Integer ferramentaId = favoritoDtoRequestUpdate.getIdFerramenta();

            if (usuarioId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId é obrigatório");
            }
            if (ferramentaId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ferramentaId é obrigatório");
            }

            Usuario usuario = usuarioRepository.obterUsuarioAtivoPorId(usuarioId);
            if (usuario == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ou inativo: " + usuarioId);
            }

            Ferramenta ferramenta = ferramentaRepository.obterFerramentaAtivaPorId(ferramentaId);
            if (ferramenta == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada ou inativa: " + ferramentaId);
            }


            favorito.setData(favoritoDtoRequestUpdate.getData());
            favorito.setUsuario(usuario);
            favorito.setFerramenta(ferramenta);
            favorito.setStatus(favoritoDtoRequestUpdate.getStatus());

            Favorito favoritoTemp = this.favoritoRepository.save(favorito);

            return modelMapper.map(favoritoTemp, FavoritoDtoResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idFavorito){
        Favorito favorito = listarFavoritoPorId(idFavorito);
        if(favorito != null){
            this.favoritoRepository.apagadorLogico(idFavorito);
        }
    }
}
