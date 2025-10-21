package br.senac.ConectaAi.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class FavoritoDtoRequest {

    private LocalDateTime data;

    @NotBlank(message = "É necessário um usuario")
    private Integer idUsuario;

    @NotBlank(message = "É necessário uma ferramenta")
    private Integer idFerramenta;

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
