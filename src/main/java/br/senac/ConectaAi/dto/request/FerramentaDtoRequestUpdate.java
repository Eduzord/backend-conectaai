package br.senac.ConectaAi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class FerramentaDtoRequestUpdate {
    @NotBlank(message = "O nome é obrigatório")
    public String nome;

    public String descricao;

    public String link;

    public boolean isPaga;

    public int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isPaga() {
        return isPaga;
    }

    public void setPaga(boolean paga) {
        isPaga = paga;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
