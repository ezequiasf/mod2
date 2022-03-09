package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Nota;

public class NotaRepository implements GenericRepository<Nota> {

    private final Connection con;

    public NotaRepository (Connection con){
        this.con = con;
    }
    @Override
    public Nota registrar(Nota nota) {
        String sqlInsert = "INSERT INTO APP_RECEITAS.NOTA (ID_NOTA, ID_RECEITA, ID_USUNOTA" +
                ", CLASSIFICACAO) VALUES (?,?,?,?)";
        String sqlIdConsult = "SELECT APP_RECEITAS.SEQ_NOTA.nextval id_nota FROM DUAL";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlIdConsult);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                nota.setId_nota(rs.getInt("id_nota"));
            }
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, nota.getId_nota());
            psmt.setInt(2, nota.getId_receita());
            psmt.setInt(3, nota.getId_usuario());
            psmt.setDouble(4, nota.getNota());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nota;
    }

    @Override
    public void atualizar(Integer id, Nota nota) {
        String sqlAtualizar = "UPDATE APP_RECEITAS.NOTA SET CLASSIFICACAO = ?" +
                " WHERE ID_NOTA = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlAtualizar);
            psmt.setDouble(1,nota.getNota());
            psmt.setInt(2, id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.NOTA WHERE ID_NOTA = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1,id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Nota encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_NOTA, ID_RECEITA, ID_USUNOTA," +
                " CLASSIFICACAO FROM APP_RECEITAS.NOTA WHERE ID_NOTA = ?";
        Nota nota = new Nota();
        try  {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                nota.setId_nota(rs.getInt("ID_NOTA"));
                nota.setId_receita(rs.getInt("ID_RECEITA"));
                nota.setId_usuario(rs.getInt("ID_USUNOTA"));
                nota.setNota(rs.getDouble("CLASSIFICACAO"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nota;
    }

    @Override
    public List<Nota> encontrarTodos() {
        String consultaTodos = "SELECT ID_NOTA, ID_RECEITA, ID_USUNOTA," +
                " CLASSIFICACAO FROM APP_RECEITAS.NOTA";
        List<Nota> notas = new ArrayList<>();

        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Nota nota = new Nota();
                nota.setId_nota(rs.getInt("ID_NOTA"));
                nota.setId_receita(rs.getInt("ID_RECEITA"));
                nota.setId_usuario(rs.getInt("ID_USUNOTA"));
                nota.setNota(rs.getDouble("CLASSIFICACAO"));
                notas.add(nota);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notas;
    }
}
