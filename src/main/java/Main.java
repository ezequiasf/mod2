import config.ConfigAmbienteJDBC;
import repository.UsuarioRepository;
import services.UsuarioService;

import java.io.IOException;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {

        Connection con = null;
        try {
            con = ConfigAmbienteJDBC.getConnection();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        UsuarioRepository usr = new UsuarioRepository(con);
        UsuarioService us = new UsuarioService(usr);



    }
}
