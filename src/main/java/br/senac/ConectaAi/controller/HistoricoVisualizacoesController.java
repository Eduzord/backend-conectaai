package br.senac.ConectaAi.controller;


import br.senac.ConectaAi.dto.request.HistoricoVisualizacoesDtoRequest;
import br.senac.ConectaAi.dto.response.HistoricoVisualizacoesDtoResponse;
import br.senac.ConectaAi.entity.HistoricoVisualizacoes;
import br.senac.ConectaAi.service.HistoricoVisualizacoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/historico")
@Tag(name = "Historico", description = "API para gerenciamento do Histórico de Visualizações")
public class HistoricoVisualizacoesController {
    @Autowired
    HistoricoVisualizacoesService historicoVisualizacoesService;

    @GetMapping("/listar")
    @Operation(summary = "Listar historico no sistema.")
    public ResponseEntity<List<HistoricoVisualizacoes>> listar(){
        return  ResponseEntity.ok(historicoVisualizacoesService.listarHistoricoVisualizacoes());
    }

    @GetMapping("/listarPorIdHistoricoVisualizacoes/{idHistoricoVisualizacoes}")
    @Operation(summary = "Listar historico de visualizações no sistema pelo Id.")
    public ResponseEntity<HistoricoVisualizacoes> listarPorIdHistoricoVisualizacoes(@PathVariable("idHistoricoVisualizacoes")Integer idHistoricoVisualizacoes){
        HistoricoVisualizacoes historicoVisualizacoes = historicoVisualizacoesService.listarHistoricoVisualizacoesPorId(idHistoricoVisualizacoes);
        if (historicoVisualizacoes == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(historicoVisualizacoes);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo histórico no sistema.")
    public ResponseEntity<HistoricoVisualizacoesDtoResponse> criar(@RequestBody HistoricoVisualizacoesDtoRequest historicoVisualizacoesDtoRequest){

        return  ResponseEntity.ok(historicoVisualizacoesService.salvar(historicoVisualizacoesDtoRequest));
    }
//    Método atualizar não implementado pois não acho necessário
//    @PutMapping("/atualizar/{idHistoricoVisualizacoes}")
//    @Operation(summary = "Atualiza todos os parametros de um histórico.")
//    public ResponseEntity<HistoricoVisualizacoesDtoResponse>atualizar(
//            @Valid @PathVariable("idHistoricoVisualizacoes") Integer idHistoricoVisualizacoes,
//            @RequestBody HistoricoVisualizacoesDtoRequestUpdate historicoVisualizacoesDtoRequestUpdate){
//        return ResponseEntity.ok(historicoVisualizacoesService.atualizar(idHistoricoVisualizacoes, historicoVisualizacoesDtoRequestUpdate));
//    }

    @DeleteMapping("/apagar/{idHistoricoVisualizacoes}")
    @Operation(summary = "Deleta um historico do sistema pelo id.")
    public String apagar(@PathVariable("idHistoricoVisualizacoes") Integer idHistoricoVisualizacoes){
        historicoVisualizacoesService.apagar(idHistoricoVisualizacoes);
        return "Historico deletado com sucesso";
    }
}
