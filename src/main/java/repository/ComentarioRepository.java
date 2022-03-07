package repository;

import model.Comentario;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioRepository implements GenericRepository<Comentario> {

    private final Connection con;

    public ComentarioRepository (Connection con){
        this.con = con;
    }

    @Override
    public Comentario registrar(Comentario comentario) {
        String sqlInsert = "INSERT INTO APP_RECEITAS.COMENTARIO (ID_COMENTARIO, ID_RECEITA, ID_USUCOMENT" +
                ", COMENTARIO) VALUES (?,?,?,?)";
        String sqlIdConsult = "SELECT APP_RECEITAS.SEQ_COMENTARIO.nextval id_coment FROM DUAL";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlIdConsult);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                comentario.setId_comentario(rs.getInt("id_coment"));
            }
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, comentario.getId_comentario());
            psmt.setInt(2, comentario.getId_receita());
            psmt.setInt(3, comentario.getId_usuario());
            psmt.setString(4, comentario.getComentario());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentario;
    }

    @Override
    public void atualizar(Integer id, Comentario comentario) {
        String sqlAtualizar = "UPDATE APP_RECEITAS.COMENTARIO SET COMENTARIO = ?" +
                " WHERE ID_COMENTARIO = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlAtualizar);
            psmt.setString(1,comentario.getComentario());
            psmt.setInt(2, id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.COMENTARIO WHERE ID_COMENTARIO = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1,id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Comentario encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_COMENTARIO, ID_RECEITA, ID_USUCOMENT, COMENTARIO" +
                " FROM APP_RECEITAS.COMENTARIO WHERE ID_COMENTARIO = ?";
        Comentario coment = new Comentario();
        try  {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                coment.setId_comentario(rs.getInt("ID_COMENTARIO"));
                coment.setId_receita(rs.getInt("ID_RECEITA"));
                coment.setId_comentario(rs.getInt("ID_USUCOMENT"));
                coment.setComentario(rs.getString("COMENTARIO"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return coment;
    }

    @Override
    public List<Comentario> encontrarTodos() {
        String consultaTodos = "SELECT ID_COMENTARIO, ID_RECEITA, ID_USUCOMENT, COMENTARIO" +
                " FROM APP_RECEITAS.COMENTARIO";
        List<Comentario> comentarios = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Comentario coment = new Comentario();
                coment.setId_comentario(rs.getInt("ID_COMENTARIO"));
                coment.setId_receita(rs.getInt("ID_RECEITA"));
                coment.setId_comentario(rs.getInt("ID_USUCOMENT"));
                coment.setComentario(rs.getString("COMENTARIO"));
                comentarios.add(coment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return comentarios;
    }

}
