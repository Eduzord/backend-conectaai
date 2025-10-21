package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.HistoricoVisualizacoesDtoRequest;
import br.senac.ConectaAi.dto.response.HistoricoVisualizacoesDtoResponse;
import br.senac.ConectaAi.entity.HistoricoVisualizacoes;
import br.senac.ConectaAi.repository.HistoricoVisualizacoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoVisualizacoesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HistoricoVisualizacoesRepository historicoVisualizacoesRepository;

    public List<HistoricoVisualizacoes> listarHistoricoVisualizacoes(){
        return this.historicoVisualizacoesRepository.listarHistoricoVizualizacoesAtivos();
    }

    public HistoricoVisualizacoes listarHistoricoVisualizacoesPorId(int idHistoricoVisualizacoes){
        return this.historicoVisualizacoesRepository.obterHistoricoVisualizacoesAtivoPorId(idHistoricoVisualizacoes);
    }

    public HistoricoVisualizacoesDtoResponse salvar(HistoricoVisualizacoesDtoRequest historicoVisualizacoesDtoRequest){
        HistoricoVisualizacoes historicoVisualizacoes = modelMapper.map(historicoVisualizacoesRepository, HistoricoVisualizacoes.class);
        historicoVisualizacoes.setStatus(1);

        HistoricoVisualizacoes historicoVisualizacoesSalvo = this.historicoVisualizacoesRepository.save(historicoVisualizacoes);

        return modelMapper.map(historicoVisualizacoesSalvo, HistoricoVisualizacoesDtoResponse.class);
    }

    //Método atualizar vai aqui, caso precise

    public void apagar(Integer idHistoricoVisualizacoes){
        HistoricoVisualizacoes historicoVisualizacoes = this.historicoVisualizacoesRepository.obterHistoricoVisualizacoesAtivoPorId(idHistoricoVisualizacoes);
        if (historicoVisualizacoes != null) {
            this.historicoVisualizacoesRepository.apagadorLogico(idHistoricoVisualizacoes);
        }else{
            System.out.println("Id não existe no banco de dados.");
        }
    }
}
