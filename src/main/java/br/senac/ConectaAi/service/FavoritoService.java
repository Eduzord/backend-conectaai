package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.FavoritoDtoRequest;
import br.senac.ConectaAi.dto.request.FavoritoDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.FavoritoDtoResponse;
import br.senac.ConectaAi.entity.Favorito;
import br.senac.ConectaAi.repository.FavoritoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FavoritoRepository favoritoRepository;



    public List<Favorito> listarFavoritos(){
        return this.favoritoRepository.listarFavoritosAtivos();
    }

    public Favorito listarFavoritoPorId(int idFavorito){
        return this.favoritoRepository.obterFavoritoAtivoPorId(idFavorito);
    }

    public FavoritoDtoResponse salvar(FavoritoDtoRequest favoritoDtoRequest){
        Favorito favorito = modelMapper.map(favoritoDtoRequest, Favorito.class);
        favorito.setStatus(1);

        Favorito favoritoSalvo = this.favoritoRepository.save(favorito);

        return modelMapper.map(favoritoSalvo, FavoritoDtoResponse.class);
    }

    public FavoritoDtoResponse atualizar(Integer idFavorito, FavoritoDtoRequestUpdate favoritoDtoRequestUpdate){
        Favorito favorito = this.listarFavoritoPorId(idFavorito);
        if(favorito != null){
            modelMapper.map(favoritoDtoRequestUpdate, favorito);
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
