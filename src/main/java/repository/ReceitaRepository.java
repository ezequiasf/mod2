package repository;

import model.Receita;
import model.Usuario;
import utils.TipoReceita;
import utils.TipoRefeicao;

import java.sql.*;
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
                ", TIPO_RECEITA, CALORIAS, MODO_PREPARO, TEMPO_PREPARO, MEDIA_PRECO" +
                ", TIPO_REFEICAO, CLASSIFICACAO) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            psmt.setDouble(10, receita.getMediaNota());
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
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Não deleta caso exista algum ingrediente o referenciando (O mesmo vale para usuário)
    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.RECEITA WHERE ID_RECEITA = ?";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Receita encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_RECEITA, ID_USUARIO, NOME_RECEITA, " +
                " TIPO_RECEITA, CALORIAS, MEDIA_PRECO, TEMPO_PREPARO" +
                ",TIPO_REFEICAO, MODO_PREPARO, CLASSIFICACAO FROM APP_RECEITAS.RECEITA WHERE ID_RECEITA = ?";
        Receita receita = new Receita();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                receita.setId_receita(rs.getInt("ID_RECEITA"));
                receita.setId_usuario(rs.getInt("ID_USUARIO"));
                receita.setNomeReceita(rs.getString("NOME_RECEITA"));
                receita.setTipoReceita(TipoReceita.valueOf(rs.getString("TIPO_RECEITA").toUpperCase()));
                receita.setCalorias(rs.getDouble("CALORIAS"));
                receita.setMediaPreco(rs.getDouble("MEDIA_PRECO"));
                receita.setTempoPreparo(rs.getInt("TEMPO_PREPARO"));
                if (rs.getString("TIPO_REFEICAO").equals("Almoço ou janta")) {
                    receita.setTipoRefeicao(TipoRefeicao.ALMOCO_JANTA);
                } else if (rs.getString("TIPO_REFEICAO").equals("Lanche")) {
                    receita.setTipoRefeicao(TipoRefeicao.LANCHE);
                } else {
                    receita.setTipoRefeicao(TipoRefeicao.CAFE);
                }
                receita.setModoPreparo(rs.getString("MODO_PREPARO"));
                receita.setMediaNota(rs.getDouble("CLASSIFICACAO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receita;
    }

    @Override
    public List<Receita> encontrarTodos() {
        String consultaTodos = "SELECT ID_RECEITA, ID_USUARIO, NOME_RECEITA," +
                " TIPO_RECEITA, CALORIAS, MEDIA_PRECO, TEMPO_PREPARO," +
                " TIPO_REFEICAO, MODO_PREPARO, CLASSIFICACAO FROM APP_RECEITAS.RECEITA";
        List<Receita> receitas = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId_receita(rs.getInt("ID_RECEITA"));
                receita.setId_usuario(rs.getInt("ID_USUARIO"));
                receita.setNomeReceita(rs.getString("NOME_RECEITA"));
                receita.setTipoReceita(TipoReceita.valueOf(rs.getString("TIPO_RECEITA").toUpperCase()));
                receita.setCalorias(rs.getDouble("CALORIAS"));
                receita.setMediaPreco(rs.getDouble("MEDIA_PRECO"));
                receita.setTempoPreparo(rs.getInt("TEMPO_PREPARO"));
                if (rs.getString("TIPO_REFEICAO").equals("Almoço ou janta")) {
                    receita.setTipoRefeicao(TipoRefeicao.ALMOCO_JANTA);
                } else if (rs.getString("TIPO_REFEICAO").equals("Lanche")) {
                    receita.setTipoRefeicao(TipoRefeicao.LANCHE);
                } else {
                    receita.setTipoRefeicao(TipoRefeicao.CAFE);
                }
                receita.setModoPreparo(rs.getString("MODO_PREPARO"));
                receita.setMediaNota(rs.getDouble("CLASSIFICACAO"));
                receitas.add(receita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receitas;
    }

    public Receita encontrarPorReferencia(Receita receita) {
        String consultaTodos = "SELECT ID_RECEITA FROM APP_RECEITAS.RECEITA WHERE NOME_RECEITA = ? " +
                "AND ID_USUARIO = ?";
        Receita receitaConsulta = new Receita();
        try {
            PreparedStatement ps = con.prepareStatement(consultaTodos);
            ps.setInt(1, receita.getId_receita());
            ps.setInt(2, receita.getId_usuario());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                receitaConsulta.setId_receita(rs.getInt("ID_RECEITA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receitaConsulta;
    }

}
