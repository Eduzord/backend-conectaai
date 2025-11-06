package br.senac.ConectaAi.dto.response;

import br.senac.ConectaAi.dto.request.FerramentaDtoRequest;
import br.senac.ConectaAi.entity.Ferramenta;
import br.senac.ConectaAi.entity.Usuario;

import java.time.LocalDateTime;

public class HistoricoVisualizacoesDtoResponse {

    private Integer id;

    private LocalDateTime data;

    private UsuarioDtoResponse usuario;

    private FerramentaDtoResponse ferramenta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public UsuarioDtoResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDtoResponse usuario) {
        this.usuario = usuario;
    }

    public FerramentaDtoResponse getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(FerramentaDtoResponse ferramenta) {
        this.ferramenta = ferramenta;
    }
}
