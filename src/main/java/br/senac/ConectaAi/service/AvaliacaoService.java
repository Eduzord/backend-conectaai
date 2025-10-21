package br.senac.ConectaAi.service;


import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequest;
import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.AvaliacaoDtoResponse;
import br.senac.ConectaAi.entity.Avaliacao;
import br.senac.ConectaAi.repository.AvaliacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarAvaliacoes(){
        return this.avaliacaoRepository.listarAvaliacoesAtivas();
    }

    public Avaliacao listarAvaliacaoPorId(int idAvaliacao){
        return this.avaliacaoRepository.obterAvaliacaoAtivaPorId(idAvaliacao);
    }

    public AvaliacaoDtoResponse salvar(AvaliacaoDtoRequest avaliacaoDtoRequest){
        Avaliacao avaliacao = modelMapper.map(avaliacaoDtoRequest, Avaliacao.class);
        avaliacao.setStatus(1);

        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(avaliacao);

        return modelMapper.map(avaliacaoSalva, AvaliacaoDtoResponse.class);
    }

    public AvaliacaoDtoResponse atualizar(Integer idAvaliacao, AvaliacaoDtoRequestUpdate avaliacaoDtoRequestUpdate){
        Avaliacao avaliacao = this.listarAvaliacaoPorId(idAvaliacao);
        if(avaliacao != null){
            modelMapper.map(avaliacaoDtoRequestUpdate, avaliacao);
            Avaliacao avaliacaoTemp = this.avaliacaoRepository.save(avaliacao);

            return modelMapper.map(avaliacaoTemp, AvaliacaoDtoResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idAvaliacao){
        Avaliacao avaliacao = listarAvaliacaoPorId(idAvaliacao);
        if(avaliacao != null){
            this.avaliacaoRepository.apagadorLogico(idAvaliacao);
        }
    }
}
