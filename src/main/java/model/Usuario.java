package model;
import java.time.LocalDate;

public class Usuario {

    private Integer id_usuario;
    private String nomeUsuario;
    private String senha;
    private LocalDate nascimento;
    private String email;

    public Usuario (){}

    public Usuario(String nomeUsuario, String senha,int anoNasc, int mesNasc, int diaNasc, String email) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        setNascimento(anoNasc, mesNasc, diaNasc);
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(int ano, int mes, int dia) {
        this.nascimento = LocalDate.of(ano, mes, dia);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Nome =" + nomeUsuario +
                ", Aniversário =" + nascimento +
                ",Email =" + email +
                '}';
    }
}
