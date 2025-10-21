package br.senac.ConectaAi.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class AvaliacaoDtoRequest {

    @NotBlank(message = "É necessário uma nota")
    private Integer nota;

    private String comentario;

    private LocalDateTime data;

    @NotBlank(message = "É necessário um usuário")
    private Integer idUsuario;

    @NotBlank(message = "É necessário uma ferramenta")
    private Integer idFerramenta;

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdFerramenta() {
        return idFerramenta;
    }

    public void setIdFerramenta(Integer idFerramenta) {
        this.idFerramenta = idFerramenta;
    }
}
