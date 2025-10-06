package br.senac.ConectaAi.controller;


import br.senac.ConectaAi.dto.request.CategoriaDtoRequest;
import br.senac.ConectaAi.dto.request.CategoriaDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.CategoriaDtoResponse;
import br.senac.ConectaAi.entity.Categoria;
import br.senac.ConectaAi.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@Tag(name="Categoria", description = "API para gerenciamento das categorias do sistema.")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar categorias no sistema.")
    public ResponseEntity<List<Categoria>> listar(){
        return  ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/listarPorIdCategoria/{idCategoria}")
    @Operation(summary = "Listar categorias no sistema pelo Id.")
    public ResponseEntity<Categoria> listarPorIdCategoria(@PathVariable("idCategoria")Integer idCategoria){
        Categoria categoria = categoriaService.listarCategoriaPorId(idCategoria);
        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categoria);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova categoria no sistema.")
    public ResponseEntity<CategoriaDtoResponse> criar(@Valid @RequestBody CategoriaDtoRequest categoriaDtoRequest){

        return  ResponseEntity.ok(categoriaService.salvar(categoriaDtoRequest));
    }

    @PutMapping("/atualizar/{idCategoria}")
    @Operation(summary = "Atualiza todos os parametros de uma categoria.")
    public ResponseEntity<CategoriaDtoResponse>atualizar(
            @Valid @PathVariable("idCategoria") Integer idCategoria,
            @RequestBody CategoriaDtoRequestUpdate categoriaDtoRequestUpdate){
        return ResponseEntity.ok(categoriaService.atualizar(idCategoria, categoriaDtoRequestUpdate));
    }

    @DeleteMapping("/apagar/{idCategoria}")
    @Operation(summary = "Deleta uma categoria do sistema pelo id.")
    public String apagar(@PathVariable("idCategoria") Integer idCategoria){
        categoriaService.apagar(idCategoria);
        return "Categoria deletada com sucesso";
    }
}