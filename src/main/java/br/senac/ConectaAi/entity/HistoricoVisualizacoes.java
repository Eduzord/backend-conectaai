package br.senac.ConectaAi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_visualizacoes")
public class HistoricoVisualizacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historico_id")
    private Integer id;

    @Column(name = "historico_visualizacoes_data")
    private LocalDateTime data;

    @Column(name = "historico_status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    Usuario usuario;


    @ManyToOne
    @JoinColumn(name="ferramenta_id")
    @JsonIgnore
    Ferramenta ferramenta;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
