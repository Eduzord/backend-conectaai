package br.senac.ConectaAi.service;


import br.senac.ConectaAi.dto.request.FerramentaDtoRequest;
import br.senac.ConectaAi.dto.request.FerramentaDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.FerramentaDtoResponse;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.repository.FerramentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FerramentaService {

    @Autowired
    private ModelMapper modelMapper;

    private FerramentaRepository ferramentaRepository;

    public FerramentaService(FerramentaRepository ferramentaRepository) {
        this.ferramentaRepository = ferramentaRepository;
    }

    public List<Ferramenta> listarFerramentas(){
        return this.ferramentaRepository.listarFerramentasAtivas();
    }

    public Ferramenta listarFerramentaPorId(int idFerramenta){
        return this.ferramentaRepository.obterFerramentaAtivaPorId(idFerramenta);
    }

    public FerramentaDtoResponse salvar(FerramentaDtoRequest ferramentaDtoRequest){
        Ferramenta ferramenta = modelMapper.map(ferramentaDtoRequest, Ferramenta.class);
        ferramenta.setStatus(1);

        Ferramenta ferramentaSalva = this.ferramentaRepository.save(ferramenta);

        return modelMapper.map(ferramentaSalva, FerramentaDtoResponse.class);
    }

    public FerramentaDtoResponse atualizar(Integer idFerramenta, FerramentaDtoRequestUpdate ferramentaDtoRequestUpdate){
        Ferramenta ferramenta = this.listarFerramentaPorId(idFerramenta);
        if(ferramenta != null){
            modelMapper.map(ferramentaDtoRequestUpdate, ferramenta);
            Ferramenta ferramentaTemp = this.ferramentaRepository.save(ferramenta);

            return modelMapper.map(ferramentaTemp, FerramentaDtoResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idFerramenta){
        Ferramenta ferramenta = listarFerramentaPorId(idFerramenta);
        if(ferramenta != null){
            this.ferramentaRepository.apagadorLogico(idFerramenta);
        }
    }
}
