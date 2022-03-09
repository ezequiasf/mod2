import config.ConfigAmbienteJDBC;
import repository.ComentarioRepository;
import repository.UsuarioRepository;
import services.UsuarioService;
import view.Tela;

import utils.BuscaReceita;
import utils.TipoReceita;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection con = ConfigAmbienteJDBC.getConnection();

//        try {
//            con = ConfigAmbienteJDBC.getConnection();
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }

        BuscaReceita br = new BuscaReceita(con);
        br.consulta(4,"ovo").forEach(arr-> System.out.println(arr[0]));
//        br.filtroUmIngrediente("OVO",4).forEach(Arr -> {
//            System.out.println(Arr[0]);
//            System.out.println(Arr[1]);
//            System.out.println(Arr[2]);
//        });

//        br.filtroTipoReceita(TipoReceita.SALGADA, 6).forEach(Arr -> System.out.println(Arr[0]));
//
//
//        br.filtroTipoReceita(TipoReceita.SALGADA, 6).forEach(Arr -> System.out.println(Arr[0]));
//
//
//        br.filtroLimiteTempo(20,3).forEach(Arr -> System.out.println(Arr[0]));
//
//        br.filtroLimitePreco(35.0, 0).forEach(Arr -> System.out.println(Arr[0]));


//        br.listaPrecosCrescente().forEach(Arr -> System.out.println(Arr[0]));
//        br.listaPrecosDecrescente().forEach(Arr -> System.out.println(Arr[0]));

//        br.filtroCalorias(5, 700, 5000).forEach(Arr -> System.out.println(Arr[0]));
//
//
////        System.out.println(br.filtroCalorias(5, 100, 2000));
//
//        UsuarioRepository usr = new UsuarioRepository(con);
//        UsuarioService us = new UsuarioService(usr);

////        Connection con = null;
////        con = ConfigAmbienteJDBC.getConnection();
////        UsuarioRepository usr = new UsuarioRepository(con);
////        UsuarioService us = new UsuarioService(usr);
//        Tela.telaPrincipal();
    }
}
