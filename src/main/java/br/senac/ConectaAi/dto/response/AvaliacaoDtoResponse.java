package br.senac.ConectaAi.dto.response;

import java.time.LocalDateTime;

public class AvaliacaoDtoResponse {
    private Integer id;

    private LocalDateTime data;

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
}
