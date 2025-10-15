package br.senac.ConectaAi.controller;

import br.senac.ConectaAi.dto.request.CategoriaDtoRequest;
import br.senac.ConectaAi.dto.request.CategoriaDtoRequestUpdate;
import br.senac.ConectaAi.dto.request.TutorialDtoRequest;
import br.senac.ConectaAi.dto.request.TutorialDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.CategoriaDtoResponse;
import br.senac.ConectaAi.dto.response.TutorialDtoResponse;
import br.senac.ConectaAi.entity.Tutorial;
import br.senac.ConectaAi.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tutorial")
@Tag(name = "Tutorial", description = "API para gerenciamento dos tutoriais")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;

    @GetMapping("/listar")
    @Operation(summary = "Listar tutoriais no sistema")
    public ResponseEntity<List<Tutorial>> listar(){
        return ResponseEntity.ok(tutorialService.listarTutoriais());
    }

    @GetMapping("/listarPorIdTutorial/{idTutorial}")
    @Operation(summary = "Listar tutorial pelo id.")
    public ResponseEntity<Tutorial> listarPorIdTutorial(@PathVariable("idTutorial")Integer idTutorial){
        Tutorial tutorial = tutorialService.listarTutorialPorId(idTutorial);
        if(tutorial != null){
            return ResponseEntity.ok(tutorial);
        }else
            return ResponseEntity.noContent().build();
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma novo Tutorial no sistema.")
    public ResponseEntity<TutorialDtoResponse> criar(@Valid @RequestBody TutorialDtoRequest tutorialDtoRequest){

        return  ResponseEntity.ok(tutorialService.salvar(tutorialDtoRequest));
    }

    @PutMapping("/atualizar/{idTutorial}")
    @Operation(summary = "Atualiza todos os parametros de um tutorial.")
    public ResponseEntity<TutorialDtoResponse>atualizar(
            @Valid @PathVariable("idTutorial") Integer idTutorial,
            @RequestBody TutorialDtoRequestUpdate tutorialDtoRequestUpdate){
        return ResponseEntity.ok(tutorialService.atualizar(idTutorial, tutorialDtoRequestUpdate));
    }

    @DeleteMapping("/apagar/{idTutorial}")
    @Operation(summary = "Deleta um tutorial do sistema pelo id.")
    public String apagar(@PathVariable("idTutorial") Integer idTutorial){
        tutorialService.apagar(idTutorial);
        return "Categoria deletada com sucesso";
    }

}
