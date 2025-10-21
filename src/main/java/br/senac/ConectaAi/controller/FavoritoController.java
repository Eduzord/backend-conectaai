package br.senac.ConectaAi.controller;

import br.senac.ConectaAi.dto.request.FavoritoDtoRequest;
import br.senac.ConectaAi.dto.request.FavoritoDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.FavoritoDtoResponse;
import br.senac.ConectaAi.entity.Favorito;
import br.senac.ConectaAi.service.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorito")
@Tag(name = "Favoritos", description = "API para gerenciamento dos favoritos.")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar favoritos ativos no sistema.")
    public ResponseEntity<List<Favorito>> listar(){
        return ResponseEntity.ok(favoritoService.listarFavoritos());
    }

    @GetMapping("/listarPorIdFavorito/{idFavorito}")
    @Operation(summary = "Listar favorito no sistema pelo Id.")
    public ResponseEntity<Favorito> listarPorIdFavorito(@PathVariable("idFavorito")Integer idFavorito) {
        Favorito favorito = favoritoService.listarFavoritoPorId(idFavorito);

        if(favorito != null){
            return ResponseEntity.ok(favorito);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo favorito no sistema")
    public ResponseEntity<FavoritoDtoResponse> criar (@Valid @RequestBody FavoritoDtoRequest favoritoDtoRequest){
        return ResponseEntity.ok(favoritoService.salvar(favoritoDtoRequest));
    }

    @PutMapping("/atualizar/{idFavorito}")
    @Operation(summary = "Atualiza todos os parametros de um favorito.")
    public ResponseEntity<FavoritoDtoResponse> atualizar(@Valid @PathVariable("idFavorito") Integer idFavorito,
                                                           @RequestBody FavoritoDtoRequestUpdate favoritoDtoRequestUpdate){
        return ResponseEntity.ok(favoritoService.atualizar(idFavorito, favoritoDtoRequestUpdate));
    }

    @DeleteMapping("/apagar/{idFavorito}")
    @Operation(summary = "Deleta um favorito do sistema pelo id.")
    public ResponseEntity<FavoritoDtoResponse> apagar(@PathVariable("idFavorito")Integer idFavorito){
        favoritoService.apagar(idFavorito);
        return ResponseEntity.noContent().build();
    }
}
