package model;

public class Comentario {

    private Integer id_comentario;
    private Integer id_receita;
    private Integer id_usuario;
    private String comentario;

    public Comentario (){}

    public Comentario(Integer id_receita, Integer id_usuario, String comentario) {
        this.id_receita = id_receita;
        this.id_usuario = id_usuario;
        this.comentario = comentario;
    }

    public Integer getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.id_comentario = id_comentario;
    }

    public Integer getId_receita() {
        return id_receita;
    }

    public void setId_receita(Integer id_receita) {
        this.id_receita = id_receita;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
