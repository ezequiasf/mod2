package repository;

import model.Ingrediente;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteRepository implements GenericRepository<Ingrediente> {

    private final Connection con;

    public IngredienteRepository(Connection con) {
        this.con = con;
    }


    @Override
    public Ingrediente registrar(Ingrediente ingrediente) {
        String sqlInsert = "INSERT INTO APP_RECEITAS.INGREDIENTE (ID_INGREDIENTE, ID_RECEITA" +
                ", NOME, QUANTIDADE)" +
                " VALUES (?,?,?,?)";
        String sqlIdConsult = "SELECT APP_RECEITAS.SEQ_INGREDIENTE.nextval id_ing FROM DUAL";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlIdConsult);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ingrediente.setId_ingrediente(rs.getInt("id_ing"));
            }
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, ingrediente.getId_ingrediente());
            psmt.setInt(2, ingrediente.getId_receita());
            psmt.setString(3, ingrediente.getNome());
            psmt.setString(4, ingrediente.getQuantidade());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingrediente;
    }

    @Override
    public void atualizar(Integer id, Ingrediente ingrediente) {
        String sqlAtualizar = "UPDATE APP_RECEITAS.INGREDIENTE SET NOME = ? , " +
                "QUANTIDADE = ? WHERE ID_INGREDIENTE = ?";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlAtualizar);
            psmt.setString(1, ingrediente.getNome());
            psmt.setString(2, ingrediente.getQuantidade());
            psmt.setInt(3, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.INGREDIENTE WHERE ID_INGREDIENTE = ?";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingrediente encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_INGREDIENTE, ID_RECEITA, NOME," +
                " QUANTIDADE FROM APP_RECEITAS.INGREDIENTE WHERE ID_INGREDIENTE = ?";
        Ingrediente ing = new Ingrediente();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ing.setId_ingrediente(rs.getInt("ID_INGREDIENTE"));
                ing.setId_receita(rs.getInt("ID_RECEITA"));
                ing.setNome(rs.getString("NOME"));
                ing.setQuantidade(rs.getString("QUANTIDADE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ing;
    }

    @Override
    public List<Ingrediente> encontrarTodos() {
        String consultaTodos = "SELECT ID_INGREDIENTE, ID_RECEITA, NOME," +
                " QUANTIDADE FROM APP_RECEITAS.INGREDIENTE";
        List<Ingrediente> ingredientes = new ArrayList<>();

        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setId_ingrediente(rs.getInt("ID_INGREDIENTE"));
                ing.setId_receita(rs.getInt("ID_RECEITA"));
                ing.setNome(rs.getString("NOME"));
                ing.setQuantidade(rs.getString("QUANTIDADE"));
                ingredientes.add(ing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    public List<Integer> encontrarPorReferencia (Ingrediente ing){
        String consultaTodos = "SELECT ID_INGREDIENTE " +
                " FROM APP_RECEITAS.INGREDIENTE WHERE ID_RECEITA = ?";
        List<Integer> idIngredienteAntigo = new ArrayList<>();
        Ingrediente ingConsulta = new Ingrediente();
        try {
            PreparedStatement ps = con.prepareStatement(consultaTodos);
            ps.setInt(1, ing.getId_receita());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                idIngredienteAntigo.add(rs.getInt("ID_INGREDIENTE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idIngredienteAntigo;
    }
}
