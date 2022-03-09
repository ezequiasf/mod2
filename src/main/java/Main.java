import config.ConfigAmbienteJDBC;
import model.*;
import repository.*;
import utils.TipoReceita;
import utils.TipoRefeicao;
import view.Tela;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection con = ConfigAmbienteJDBC.getConnection();
//        ReceitaRepository rRepo = new ReceitaRepository(con);
        Tela.telaPrincipal();
    }
}
