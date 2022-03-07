package repository;

import config.ConfigAmbienteJDBC;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepository implements GenericRepository<Usuario> {

    private final Connection con;

    public UsuarioRepository(Connection con){
        this.con = con;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        String sqlInsert = "INSERT INTO APP_RECEITAS.USUARIO (ID_USUARIO, NOME_USUARIO, SENHA" +
                ", NASCIMENTO, EMAIL) VALUES (?,?,?,?,?)";
        String sqlIdConsult = "SELECT APP_RECEITAS.SEQ_USUARIO.nextval id_user FROM DUAL";
        try {
            PreparedStatement psmt = con.prepareStatement(sqlIdConsult);
            ResultSet rs = psmt.executeQuery();
            usuario.setId(rs.getInt("id_user"));
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, usuario.getId());
            psmt.setString(2, usuario.getUsuario());
            psmt.setString(3, usuario.getSenha());
            psmt.setDate(4, usuario.getNascimento());
            psmt.setString(5,usuario.getEmail());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void atualizar(Integer id, Usuario usuario) {

    }

    @Override
    public void deletar(Integer id) {

    }

    @Override
    public Usuario encontrarUm(Integer id) {
        return null;
    }

    @Override
    public List<Usuario> encontrarTodos() {
        return null;
    }

    @Override
    public List<Usuario> encontrarPorObjeto(Object obj) {
        return null;
    }
}
