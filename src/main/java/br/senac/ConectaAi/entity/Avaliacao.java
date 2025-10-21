package br.senac.ConectaAi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avaliacao_id")
    private Integer id;

    @Column(name = "avaliacao_nota")
    private Integer nota;

    @Column(name = "avaliacao_comentario")
    private String comentario;

    @Column(name = "avaliacao_data")
    private LocalDateTime data;

    @Column(name = "avaliacao_status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ferramenta_id")
    @JsonIgnore
    Ferramenta ferramenta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }
}
