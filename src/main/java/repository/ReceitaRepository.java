package repository;

import model.Receita;
import model.Usuario;
import utils.TipoReceita;
import utils.TipoRefeicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceitaRepository implements GenericRepository<Receita> {

    private final Connection con;

    public ReceitaRepository(Connection con) {
        this.con = con;
    }

    @Override
    public Receita registrar(Receita receita) {
        String sqlInsert = "INSERT INTO APP_RECEITAS.RECEITA (ID_RECEITA, ID_USUARIO, NOME_RECEITA" +
                ", TIPO_RECEITA, CALORIAS, MODO_PREPARO, TEMPO_PREPARO, MEDIA_PRECO, TIPO_REFEICAO) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        String sqlIdConsult = "SELECT APP_RECEITAS.SEQ_RECEITA.nextval id_receita FROM DUAL";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlIdConsult);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                receita.setId_receita(rs.getInt("id_receita"));
            }
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, receita.getId_receita());
            psmt.setInt(2, receita.getId_usuario());
            psmt.setString(3, receita.getNomeReceita());
            psmt.setString(4, receita.getTipoReceita().getTipo());
            psmt.setDouble(5, receita.getCalorias());
            psmt.setString(6, receita.getModoPreparo());
            psmt.setInt(7, receita.getTempoPreparo());
            psmt.setDouble(8, receita.getMediaPreco());
            psmt.setString(9, receita.getTipoRefeicao().getRefeicao());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receita;
    }

    @Override
    public void atualizar(Integer id, Receita receita) {
        String sqlAtualizar = "UPDATE APP_RECEITAS.RECEITA SET NOME_RECEITA = ?, " +
                "TIPO_RECEITA = ?, CALORIAS = ?, MODO_PREPARO = ?, TEMPO_PREPARO = ?" +
                ", MEDIA_PRECO = ?, TIPO_REFEICAO = ? WHERE ID_RECEITA = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlAtualizar);
            psmt.setString(1, receita.getNomeReceita());
            psmt.setString(2, receita.getTipoReceita().getTipo());
            psmt.setDouble(3, receita.getCalorias());
            psmt.setString(4, receita.getModoPreparo());
            psmt.setInt(5, receita.getTempoPreparo());
            psmt.setDouble(6, receita.getMediaPreco());
            psmt.setString(7, receita.getTipoRefeicao().getRefeicao());
            psmt.setInt(8, id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.RECEITA WHERE ID_RECEITA = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1,id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Receita encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_RECEITA, ID_USUARIO, NOME_RECEITA, " +
                " TIPO_RECEITA, CALORIAS, MEDIA_PRECO, TEMPO_PREPARO" +
                ",TIPO_REFEICAO, MODO_PREPARO FROM APP_RECEITAS.RECEITA WHERE ID_RECEITA = ?";
        Receita receita = new Receita();
        try  {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                receita.setId_receita(rs.getInt("ID_RECEITA"));
                receita.setId_usuario(rs.getInt("ID_USUARIO"));
                receita.setNomeReceita(rs.getString("NOME_RECEITA"));
                receita.setTipoReceita(TipoReceita.valueOf(rs.getString("TIPO_RECEITA").toUpperCase()));
                receita.setCalorias(rs.getDouble("CALORIAS"));
                receita.setMediaPreco(rs.getDouble("MEDIA_PRECO"));
                receita.setTempoPreparo(rs.getInt("TEMPO_PREPARO"));
                receita.setTipoRefeicao(TipoRefeicao.valueOf(rs.getString("TIPO_REFEICAO").toUpperCase()));
                receita.setModoPreparo(rs.getString("MODO_PREPARO"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return receita;
    }

    @Override
    public List<Receita> encontrarTodos() {
        String consultaTodos = "SELECT ID_RECEITA, ID_USUARIO, NOME_RECEITA," +
                " TIPO_RECEITA, CALORIAS, MEDIA_PRECO, TEMPO_PREPARO," +
                " TIPO_REFEICAO, MODO_PREPARO FROM APP_RECEITAS.RECEITA";
        List<Receita> receitas = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Receita receita = new Receita();
                receita.setId_receita(rs.getInt("ID_RECEITA"));
                receita.setId_usuario(rs.getInt("ID_USUARIO"));
                receita.setNomeReceita(rs.getString("NOME_RECEITA"));
                receita.setTipoReceita(TipoReceita.valueOf(rs.getString("TIPO_RECEITA").toUpperCase()));
                receita.setCalorias(rs.getDouble("CALORIAS"));
                receita.setMediaPreco(rs.getDouble("MEDIA_PRECO"));
                receita.setTempoPreparo(rs.getInt("TEMPO_PREPARO"));
                receita.setTipoRefeicao(TipoRefeicao.valueOf(rs.getString("TIPO_REFEICAO").toUpperCase()));
                receita.setModoPreparo(rs.getString("MODO_PREPARO"));
                receitas.add(receita);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return receitas;
    }

}
