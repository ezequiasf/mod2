package model;

public class Ingrediente {
    private Integer id_ingrediente;
    private Integer id_receita;
    private String nome;
    private String quantidade;

    public Ingrediente (){}

    public Ingrediente(Integer id_receita, String nome, String quantidade) {
        this.id_receita = id_receita;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Integer getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(Integer id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public Integer getId_receita() {
        return id_receita;
    }

    public void setId_receita(Integer id_receita) {
        this.id_receita = id_receita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nome = " + nome +
                ", quantidade = " + quantidade +
                '}';
    }
}
