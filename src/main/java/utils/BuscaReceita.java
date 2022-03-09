package utils;

import model.Ingrediente;
import model.Receita;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuscaReceita {

    private final List<String> consultasFiltro =
            SQLReader.getSQLStatement("scriptsFiltro.sql");
    private final Connection con;

    public BuscaReceita(Connection con) {
        this.con = con;
    }

    public List<Object[]> consulta(Integer index, Object... opcoes) {
        List<Object[]> informacoes = new ArrayList<>();
        try {
            PreparedStatement pstm = con.prepareStatement(consultasFiltro.get(index));
            int contador = 1;
            for (Object obj : opcoes) {
                if (obj instanceof String)
                    pstm.setString(contador, (String) obj);
                else if (obj instanceof Integer)
                    pstm.setInt(contador, (Integer) obj);
                else if (obj instanceof Double)
                    pstm.setDouble(contador, (Double) obj);
                else if (obj instanceof  TipoReceita){
                    TipoReceita tp = (TipoReceita) obj;
                    pstm.setString(1, tp.getTipo());
                }else if (obj instanceof  TipoRefeicao){
                    TipoRefeicao tr = (TipoRefeicao) obj;
                    pstm.setString(1, tr.getRefeicao());
                }
                contador++;
            }
            informacoes = constroiLista(pstm);

        } catch(SQLException e){
                e.printStackTrace();
            }
        return informacoes;
        }

    public List<Object[]> consulta(Object opcao, Integer index) {
        List<Object[]> informacoes = new ArrayList<>();

        try {
            PreparedStatement pstm = con.prepareStatement(consultasFiltro.get(index));
            if (opcao instanceof String)
                pstm.setString(1, (String) opcao);
            else if (opcao instanceof Integer)
                pstm.setInt(1, (Integer) opcao);
            else if (opcao instanceof Double)
                pstm.setDouble(1, (Double) opcao);
            else if (opcao instanceof  TipoReceita){
                TipoReceita tp = (TipoReceita) opcao;
                pstm.setString(1, tp.getTipo());
            }else if (opcao instanceof  TipoRefeicao){
                TipoRefeicao tr = (TipoRefeicao) opcao;
                pstm.setString(1, tr.getRefeicao());
            }
            informacoes = constroiLista(pstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informacoes;
    }

    private List<Object[]> constroiLista(PreparedStatement ps) throws SQLException {
        List<Object[]> informacoes = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Receita receita = new Receita();
            Ingrediente ing = new Ingrediente();
            Usuario us = new Usuario();
            receita.setId_receita(rs.getInt("ID_RECEITA"));
            us.setUsuario(rs.getString("NOME_USUARIO"));
            receita.setNomeReceita(rs.getString("NOME_RECEITA"));
            receita.setTipoReceita(TipoReceita
                    .valueOf(rs.getString("TIPO_RECEITA").toUpperCase()));
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
            receita.setMediaNota(rs.getDouble("CLASSIFICACAO"));

            receita.setModoPreparo(rs.getString("MODO_PREPARO"));
            ing.setNome(rs.getString("NOME"));
            ing.setQuantidade(rs.getString("QUANTIDADE"));
            informacoes.add(new Object[]{receita, ing, us});
        }
        return informacoes;
    }

    public List<Object[]> listaPrecosCrescente() {
        List<Object[]> informacoes = new ArrayList<>();
        try {
            PreparedStatement pstm = con.prepareStatement(consultasFiltro.get(1));
            informacoes = constroiLista(pstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informacoes;
    }

    public List<Object[]> listaPrecosDecrescente() {
        List<Object[]> informacoes = new ArrayList<>();
        try {
            PreparedStatement pstm = con.prepareStatement(consultasFiltro.get(2));
            informacoes = constroiLista(pstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informacoes;
    }
}
