package br.senac.ConectaAi.controller;

import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequest;
import br.senac.ConectaAi.dto.request.AvaliacaoDtoRequestUpdate;
import br.senac.ConectaAi.dto.request.CategoriaDtoRequest;
import br.senac.ConectaAi.dto.request.CategoriaDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.AvaliacaoDtoResponse;
import br.senac.ConectaAi.dto.response.CategoriaDtoResponse;
import br.senac.ConectaAi.entity.Avaliacao;
import br.senac.ConectaAi.entity.Categoria;
import br.senac.ConectaAi.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacao")
@Tag(name = "Avaliações", description = "API para gerenciamento das avaliações no sistema.")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/listar")
    @Operation(summary = "Listar avaliações no sistema.")
    public ResponseEntity<List<Avaliacao>> listar(){
        return  ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
    }

    @GetMapping("/listarPorIdAvaliacao/{idAvaliacao}")
    @Operation(summary = "Listar avaliações no sistema pelo Id.")
    public ResponseEntity<Avaliacao> listarPorIdAvaliacao(@PathVariable("idAvaliacao")Integer idAvaliacao){
        Avaliacao avaliacao = avaliacaoService.listarAvaliacaoPorId(idAvaliacao);
        if (avaliacao == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(avaliacao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova avaliacao no sistema.")
    public ResponseEntity<AvaliacaoDtoResponse> criar(@Valid @RequestBody AvaliacaoDtoRequest avaliacaoDtoRequest){

        return  ResponseEntity.ok(avaliacaoService.salvar(avaliacaoDtoRequest));
    }

    @PutMapping("/atualizar/{idAvaliacao}")
    @Operation(summary = "Atualiza todos os parametros de uma avaliacao.")
    public ResponseEntity<AvaliacaoDtoResponse>atualizar(
            @Valid @PathVariable("idAvaliacao") Integer idAvaliacao,
            @RequestBody AvaliacaoDtoRequestUpdate avaliacaoDtoRequestUpdate){
        return ResponseEntity.ok(avaliacaoService.atualizar(idAvaliacao, avaliacaoDtoRequestUpdate));
    }

    @DeleteMapping("/apagar/{idAvaliacao}")
    @Operation(summary = "Deleta uma avaliacao do sistema pelo id.")
    public String apagar(@PathVariable("idAvaliacao") Integer idAvaliacao){
        avaliacaoService.apagar(idAvaliacao);
        return "Avaliacao deletada com sucesso";
    }
}
