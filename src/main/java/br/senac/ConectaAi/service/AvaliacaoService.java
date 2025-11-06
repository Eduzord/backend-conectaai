package br.senac.ConectaAi.service;


import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequest;
import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.AvaliacaoDtoResponse;
import br.senac.ConectaAi.entity.Avaliacao;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.entity.Usuario;
import br.senac.ConectaAi.repository.AvaliacaoRepository;
import br.senac.ConectaAi.repository.FerramentaRepository;
import br.senac.ConectaAi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FerramentaRepository ferramentaRepository;

    public List<Avaliacao> listarAvaliacoes(){
        return this.avaliacaoRepository.listarAvaliacoesAtivas();
    }

    public Avaliacao listarAvaliacaoPorId(int idAvaliacao){
        return this.avaliacaoRepository.obterAvaliacaoAtivaPorId(idAvaliacao);
    }

    public AvaliacaoDtoResponse salvar(AvaliacaoDtoRequest avaliacaoDtoRequest){
        Integer usuarioId = avaliacaoDtoRequest.getIdUsuario();
        Integer ferramentaId = avaliacaoDtoRequest.getIdFerramenta();

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

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDtoRequest.getNota());
        avaliacao.setComentario(avaliacaoDtoRequest.getComentario());
        avaliacao.setData(avaliacaoDtoRequest.getData());
        avaliacao.setUsuario(usuario);
        avaliacao.setFerramenta(ferramenta);

        avaliacao.setStatus(1);

        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(avaliacao);

        return modelMapper.map(avaliacaoSalva, AvaliacaoDtoResponse.class);
    }

    public AvaliacaoDtoResponse atualizar(Integer idAvaliacao, AvaliacaoDtoRequestUpdate avaliacaoDtoRequestUpdate){
        Avaliacao avaliacao = this.listarAvaliacaoPorId(idAvaliacao);
        if(avaliacao != null){
            Integer usuarioId = avaliacaoDtoRequestUpdate.getIdUsuario();
            Integer ferramentaId = avaliacaoDtoRequestUpdate.getIdFerramenta();

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

            avaliacao.setNota(avaliacaoDtoRequestUpdate.getNota());
            avaliacao.setComentario(avaliacaoDtoRequestUpdate.getComentario());
            avaliacao.setData(avaliacaoDtoRequestUpdate.getData());
            avaliacao.setUsuario(usuario);
            avaliacao.setFerramenta(ferramenta);
            avaliacao.setStatus(avaliacaoDtoRequestUpdate.getStatus());


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
