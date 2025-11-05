package br.senac.ConectaAi.controller;

import br.senac.ConectaAi.dto.request.UsuarioDtoRequest;
import br.senac.ConectaAi.dto.request.UsuarioDtoUpdateRequest;
import br.senac.ConectaAi.dto.response.UsuarioDtoResponse;
import br.senac.ConectaAi.entity.Usuario;
import br.senac.ConectaAi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name="Usuários", description ="API para gerenciamento dos usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios ativos no sistema.")
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listarPorIdUsuario/{idUsuario}")
    @Operation(summary = "Listar usuarios do sistema pelo Id.")
    public ResponseEntity<Usuario> listarPorIdUsuario(@PathVariable("idUsuario")Integer idUsuario){
        Usuario usuario = usuarioService.listarUsuarioPorId(idUsuario);

        if (usuario != null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo usuário no sistema.")
    public ResponseEntity<UsuarioDtoResponse> criar (@Valid @RequestBody UsuarioDtoRequest usuarioDtoRequest){
        return ResponseEntity.ok(usuarioService.salvar(usuarioDtoRequest));
    }

    @PutMapping("/atualizar/{idUsuario}")
    @Operation(summary = "Atualiza todos os parametros de um usuário.")
    public ResponseEntity<UsuarioDtoResponse> atualizar(@Valid @PathVariable("idUsuario") Integer idUsuario,
                                                        @RequestBody UsuarioDtoUpdateRequest usuarioDtoUpdateRequest){
        return ResponseEntity.ok(usuarioService.atualizar(idUsuario, usuarioDtoUpdateRequest));
    }

    @DeleteMapping("/apagar/{idUsuario}")
    @Operation(summary = "Deleta um usuário do sistema pelo id.")
    public ResponseEntity<UsuarioDtoResponse> apagar(@PathVariable("idUsuario") Integer idUsuario){
        usuarioService.apagar(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
