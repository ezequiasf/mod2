package model;

public class Nota {

    private Integer id_nota;
    private Integer id_receita;
    private Integer id_usuario;
    private Double nota;

    public Nota() {
    }

    public Nota(Integer id_receita, Integer id_usuario, Double nota) {
        this.id_receita = id_receita;
        this.id_usuario = id_usuario;
        this.setNota(nota);
    }

    public Integer getId_nota() {
        return id_nota;
    }

    public void setId_nota(Integer id_nota) {
        this.id_nota = id_nota;
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

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        if (nota > 5.0 || nota < 0.0) {
            System.out.println("Nota inválida. Informe uma nota entre 0 e 5.");
        } else {
            this.nota = nota;
        }
    }
}
