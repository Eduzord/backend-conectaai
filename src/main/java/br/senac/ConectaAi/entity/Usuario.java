package br.senac.ConectaAi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;

    @Column(name= "usuario_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "usuario_email",nullable = false,length = 100)
    private String email;

    @Column(name = "usuario_senha",nullable = false)
    private String senha;

    @Column(name="usuario_google_id")
    private String googleId;

    @Column(name="usuario_data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name="usuario_status",nullable = false)
    private int status;

    @OneToMany(mappedBy = "usuario")
    private List<HistoricoVisualizacoes> historico;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<HistoricoVisualizacoes> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoVisualizacoes> historico) {
        this.historico = historico;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

