package br.senac.ConectaAi.dto.request;

public class CategoriaDtoRequestUpdate {
    private String nome;
    private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
