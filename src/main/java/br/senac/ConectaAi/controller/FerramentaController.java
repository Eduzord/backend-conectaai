package br.senac.ConectaAi.controller;

import br.senac.ConectaAi.dto.request.FerramentaDtoRequest;
import br.senac.ConectaAi.dto.request.FerramentaDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.FerramentaDtoResponse;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.service.FerramentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ferramenta")
@Tag(name="Ferramentas", description = "API para gerenciamento das ferramentas.")
public class FerramentaController {

    private final FerramentaService ferramentaService;

    public FerramentaController(FerramentaService ferramentaService) {
        this.ferramentaService = ferramentaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar ferramentas ativas no sistema.")
    public ResponseEntity<List<Ferramenta>> listar(){
        return ResponseEntity.ok(ferramentaService.listarFerramentas());
    }

    @GetMapping("/listarPorIdFerramenta/{idFerramenta}")
    @Operation(summary = "Listar ferramenta no sistema pelo Id.")
    public ResponseEntity<Ferramenta> listarPorIdFerramenta(@PathVariable("idFerramenta")Integer idFerramenta) {
        Ferramenta ferramenta = ferramentaService.listarFerramentaPorId(idFerramenta);

        if(ferramenta != null){
            return ResponseEntity.ok(ferramenta);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova ferramenta no sistema")
    public ResponseEntity<FerramentaDtoResponse> criar (@Valid @RequestBody FerramentaDtoRequest ferramentaDtoRequest){
        return ResponseEntity.ok(ferramentaService.salvar(ferramentaDtoRequest));
    }

    @PutMapping("/atualizar/{idFerramenta}")
    @Operation(summary = "Atualiza todos os parametros de uma ferramenta.")
    public ResponseEntity<FerramentaDtoResponse> atualizar(@Valid @PathVariable("idFerramenta") Integer idFerramenta,
                                                           @RequestBody FerramentaDtoRequestUpdate ferramentaDtoRequestUpdate){
        return ResponseEntity.ok(ferramentaService.atualizar(idFerramenta, ferramentaDtoRequestUpdate));
    }

    @DeleteMapping("/apagar/{idFerramenta}")
    @Operation(summary = "Deleta uma ferramenta do sistema pelo id.")
    public ResponseEntity<FerramentaDtoResponse> apagar(@PathVariable("idFerramenta")Integer idFerramenta){
        ferramentaService.apagar(idFerramenta);
        return ResponseEntity.noContent().build();
    }
}
