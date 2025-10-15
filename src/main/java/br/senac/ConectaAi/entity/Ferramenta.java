package br.senac.ConectaAi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ferramenta")
public class Ferramenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ferramenta_id")
    private int id;

    @Column(name = "ferramenta_nome",nullable = false,length = 45)
    private String nome;

    @Column(name = "ferramenta_descricao")
    private String descricao;

    @Column(name = "ferramenta_link")
    private String link;

    @Column(name = "ferramenta_is_paga")
    private boolean isPaga;

    @Column(name = "ferramenta_media_avaliacao")
    private Float mediaAvaliacao;

    @Column(name = "ferramenta_status")
    private int status;

    @OneToMany(mappedBy = "ferramenta")
    private List<Tutorial> tutorial;

    @OneToMany(mappedBy = "ferramenta")
    private List<HistoricoVisualizacoes> historico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Float getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Float mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Tutorial> getTutorial() {
        return tutorial;
    }

    public void setTutorial(List<Tutorial> tutorial) {
        this.tutorial = tutorial;
    }
}
