import config.ConfigAmbienteJDBC;
import model.*;
import repository.*;
import utils.TipoReceita;
import utils.TipoRefeicao;
import view.Tela;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        HashSet<String> str = new HashSet<>();
//
//        List<Integer> n = new ArrayList<>();
//        n.add(1);
//        n.add(1);
//        n.add(2);
//        n.add(2);
//        n.add(3);
//        n.stream().distinct().forEach(System.out::println);
////        str.add("string");
////        str.add("string");
////        str.add("string");
////        str.add("string");
////        System.out.println(str);
        Connection con = ConfigAmbienteJDBC.getConnection();
//////        ReceitaRepository rRepo = new ReceitaRepository(con);
        Tela.telaPrincipal();
    }
}
