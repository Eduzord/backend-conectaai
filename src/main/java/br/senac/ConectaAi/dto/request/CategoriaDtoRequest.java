package br.senac.ConectaAi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDtoRequest {

    @NotBlank(message = "o nome é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}