package utils;

import config.ConfigAmbienteJDBC;
import repository.*;
import services.*;

import java.sql.Connection;

public class BuildService {

    private final static Connection con = ConfigAmbienteJDBC.getConnection();

    public static ReceitaService buildReceitaService (){
        ReceitaRepository repo = new ReceitaRepository(con);
        return new ReceitaService(repo);
    }

    public static UsuarioService buildUsuarioService (){
        UsuarioRepository repo = new UsuarioRepository(con);
        return new UsuarioService(repo);
    }

    public static NotaService buildNotaService (){
        NotaRepository repo = new NotaRepository(con);
        return new NotaService(repo);
    }

    public static ComentarioService buildComentarioService (){
        ComentarioRepository repo = new ComentarioRepository(con);
        return new ComentarioService(repo);
    }

    public static IngredienteService buildIngredienteService (){
        IngredienteRepository repo = new IngredienteRepository(con);
        return new IngredienteService(repo);
    }

}
