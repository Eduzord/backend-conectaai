package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.HistoricoVisualizacoesDtoRequest;
import br.senac.ConectaAi.dto.response.HistoricoVisualizacoesDtoResponse;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.entity.HistoricoVisualizacoes;
import br.senac.ConectaAi.entity.Usuario;
import br.senac.ConectaAi.repository.FerramentaRepository;
import br.senac.ConectaAi.repository.HistoricoVisualizacoesRepository;
import br.senac.ConectaAi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoVisualizacoesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HistoricoVisualizacoesRepository historicoVisualizacoesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FerramentaRepository ferramentaRepository;

    public List<HistoricoVisualizacoesDtoResponse> listarHistoricoVisualizacoes(){
        List<HistoricoVisualizacoes> list = historicoVisualizacoesRepository.listarHistoricoVizualizacoesAtivos();
        return list.stream()
                .map(h -> modelMapper.map(h, HistoricoVisualizacoesDtoResponse.class))
                .collect(Collectors.toList());
    }

    public HistoricoVisualizacoes listarHistoricoVisualizacoesPorId(int idHistoricoVisualizacoes){
        return this.historicoVisualizacoesRepository.obterHistoricoVisualizacoesAtivoPorId(idHistoricoVisualizacoes);
    }

    public HistoricoVisualizacoesDtoResponse salvar(HistoricoVisualizacoesDtoRequest historicoVisualizacoesDtoRequest){

        Integer usuarioId = historicoVisualizacoesDtoRequest.getIdUsuario();
        Integer ferramentaId = historicoVisualizacoesDtoRequest.getIdFerramenta();

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

        HistoricoVisualizacoes historico = new HistoricoVisualizacoes();
        historico.setUsuario(usuario);
        historico.setFerramenta(ferramenta);
        historico.setData(LocalDateTime.now());
        historico.setStatus(1);
        HistoricoVisualizacoes historicoVisualizacoesSalvo = this.historicoVisualizacoesRepository.save(historico);

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
