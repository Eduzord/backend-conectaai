package br.senac.ConectaAi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class TutorialDtoRequest {

    @NotBlank(message = "Um título é obrigatório")
    private String titulo;

    @NotBlank(message = "Um conteúdo é obrigatório")
    private String conteudo;

    private String link;

    @NotBlank(message = "Uma ferramenta é obrigatória")
    private Integer ferramentaId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getFerramentaId() {
        return ferramentaId;
    }

    public void setFerramentaId(Integer ferramentaId) {
        this.ferramentaId = ferramentaId;
    }
}
