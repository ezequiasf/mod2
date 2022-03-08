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

    public BuscaReceita (Connection con){
        this.con = con;
    }

    private List<Object[]> consulta (Object opcao){
        List<Object[]> informacoes = new ArrayList<>();
        try {
            PreparedStatement pstm = con.prepareStatement(consultasFiltro.get(4));
            if (opcao instanceof String)
                pstm.setString(1,(String)opcao);
            else if (opcao instanceof Integer)
                pstm.setInt(1, (Integer) opcao);
            else if (opcao instanceof Double)
                pstm.setDouble(1, (Double) opcao);
            informacoes = constroiLista(pstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informacoes;
    }

    private List<Object[]> constroiLista (PreparedStatement ps) throws SQLException {
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
            receita.setTipoRefeicao(TipoRefeicao
                    .valueOf(rs.getString("TIPO_REFEICAO").toUpperCase()));
            receita.setModoPreparo(rs.getString("MODO_PREPARO"));
            ing.setNome(rs.getString("NOME"));
            ing.setQuantidade(rs.getString("MODO_PREPARO"));
            informacoes.add(new Object[]{receita, ing, us});
        }
        return informacoes;
    }

    public List<Object[]> filtroUmIngrediente(String ingrediente) {
        return consulta(ingrediente);
    }

    public List<Object[]> filtroTipoReceita(TipoReceita tipo) {
        return consulta(tipo.getTipo());
    }

    public List<Object[]> filtroLimiteTempo(Integer tempo) {
        return consulta(tempo);
    }

    public List<Object[]> filtroLimitePreco(Double preco) {
        return consulta(preco);
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
    //TODO: Filtro de nota

//    public static List<Receita> filtroAlmoco(List<Receita> lista, Double calorias) {
//        return filtroRefeicao(lista, calorias, 0.3, TipoRefeicao.ALMOCO_JANTA,0.15);
//    }
//
//
//    public static List<Receita> filtroLancheCafe(List<Receita> lista, Double calorias, int tipo) {
//
//        if (tipo == 1) {
//            return filtroRefeicao(lista, calorias, 0.2, TipoRefeicao.CAFE,0.10);
//        } else if (tipo == -1) {
//            return filtroRefeicao(lista, calorias, 0.2, TipoRefeicao.LANCHE,0.10);
//        }
//        return null;
//    }
//
//    private static List<Receita> filtroRefeicao(List<Receita> lista, Double calorias, Double porcentagem
//            , TipoRefeicao tipoRefeicao, Double porcentagemDelta) {
//
//        return lista.stream().filter(r -> {
//            if (r.getTipoRefeicao() == tipoRefeicao) {
//                return (r.getCalorias() > calorias * porcentagemDelta) && (r.getCalorias() <= calorias * porcentagem);
//            }
//            return false;
//        }).collect(Collectors.toList());
//    }
//
//    /**
//     * Método que escolhe aleatoriamente um tipo de refeição da lista passada.
//     *
//     * @param lista    A lista que se deseja captar os tipos de refeição aleatórios.
//     * @param calorias O total de calorias que a pessoa deve consumir.
//     * @return Uma lista com 4 receitas com tipos de refeição diferentes.
//     */
//    public static List<Receita> cardapioDoDia(List<Receita> lista, Double calorias) {
//
//        Random random = new Random();
//        List<Receita> cafes = filtroLancheCafe(lista, calorias, 1);
//        Receita cafe = null;
//        if (!(cafes.size()<=0)){
//            cafe = cafes.get(random.nextInt(cafes.size()));
//        }
//
//        List<Receita> lanches = filtroLancheCafe(lista, calorias, -1);
//        Receita lanche = null;
//        if (!(lanches.size()<=0)){
//            lanche = lanches.get(random.nextInt(lanches.size()));
//        }
//
//        List<Receita> jantas = filtroAlmoco(lista, calorias);
//        Receita janta = null;
//        if (!(jantas.size()<=0)){
//            janta = jantas.get(random.nextInt(jantas.size()));
//        }
//
//        List<Receita> almocos = filtroAlmoco(lista, calorias);
//        Receita almoco = null;
//        if (!(almocos.size()<=0)){
//           almoco = almocos.get(random.nextInt(almocos.size()));
//        }
//        return new ArrayList<>(Arrays.asList(cafe, almoco, lanche, janta));
//    }
//
//    /**
//     * Calcula a soma de todas as calorias das receitas da lista.
//     *
//     * @param receitas A lista de receitas que se deseja calcular.
//     * @return A soma de todas as calorias.
//     */
//    public static Double totalCalorias (List<Receita> receitas){
//        return receitas.stream().mapToDouble(r-> {
//            if(r!=null){
//               return r.getCalorias();
//            }
//            return 0.0;
//        }).sum();
//    }
}
