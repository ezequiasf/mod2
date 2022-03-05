package model;

import java.sql.Date;

public class Usuario {

    private Integer id_usuario;
    private String nomeUsuario;
    private String senha;
    private Date nascimento;
    private String email;

    public Usuario (){}

    public Usuario(int id_usuario, String nomeUsuario, String senha, Date nascimento, String email) {
        this.id_usuario = id_usuario;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.nascimento = nascimento;
        this.email = email;
    }

    public Integer getId() {
        return id_usuario;
    }

    public void setId(int id) {
        this.id_usuario = id;
    }

    public String getUsuario() {
        return nomeUsuario;
    }

    public void setUsuario(String usuario) {
        this.nomeUsuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
