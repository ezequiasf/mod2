package repository;

import model.Usuario;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
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
            while (rs.next()) {
                usuario.setId(rs.getInt("id_user"));
            }
            psmt = con.prepareStatement(sqlInsert);
            psmt.setInt(1, usuario.getId());
            psmt.setString(2, usuario.getUsuario());
            psmt.setString(3, usuario.getSenha());
            psmt.setDate(4, Date.valueOf(usuario.getNascimento()));
            psmt.setString(5, usuario.getEmail());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void atualizar(Integer id, Usuario usuario) {
        String sqlAtualizar = "UPDATE APP_RECEITAS.USUARIO SET NOME_USUARIO = ?, " +
                "SENHA = ?, NASCIMENTO = ?, EMAIL = ? WHERE ID_USUARIO = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlAtualizar);
            psmt.setString(1,usuario.getUsuario());
            psmt.setString(2,usuario.getSenha());
            psmt.setDate(3, Date.valueOf(usuario.getNascimento()));
            psmt.setString(4, usuario.getEmail());
            psmt.setInt(5, id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Integer id) {
        String sqlDeletar = "DELETE FROM APP_RECEITAS.USUARIO WHERE ID_USUARIO = ?";
        try  {
            PreparedStatement psmt = con.prepareStatement(sqlDeletar);
            psmt.setInt(1,id);
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Usuario encontrarUm(Integer id) {
        String consultaUm = "SELECT ID_USUARIO, NOME_USUARIO, NASCIMENTO," +
                "SENHA, EMAIL FROM APP_RECEITAS.USUARIO WHERE ID_USUARIO = ?";
        Usuario us = new Usuario();
        try  {
            PreparedStatement psmt = con.prepareStatement(consultaUm);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Date dt = rs.getDate("NASCIMENTO");
                int ano = dt.toLocalDate().getYear();
                int mes = dt.toLocalDate().getDayOfMonth();
                int dia = dt.toLocalDate().getDayOfMonth();
                us.setId(rs.getInt("ID_USUARIO"));
                us.setUsuario(rs.getString("NOME_USUARIO"));
                us.setNascimento(ano,mes,dia);
                us.setSenha(rs.getString("SENHA"));
                us.setEmail(rs.getString("EMAIL"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return us;
    }

    @Override
    public List<Usuario> encontrarTodos() {
        String consultaTodos = "SELECT ID_USUARIO, NOME_USUARIO, NASCIMENTO," +
                " SENHA, EMAIL FROM APP_RECEITAS.USUARIO";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement psmt = con.prepareStatement(consultaTodos);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Usuario us = new Usuario();
                Date dt = rs.getDate("NASCIMENTO");
                int ano = dt.toLocalDate().getYear();
                int mes = dt.toLocalDate().getDayOfMonth();
                int dia = dt.toLocalDate().getDayOfMonth();
                us.setId(rs.getInt("ID_USUARIO"));
                us.setUsuario(rs.getString("NOME_USUARIO"));
                us.setNascimento(ano,mes,dia);
                us.setSenha(rs.getString("SENHA"));
                us.setEmail(rs.getString("EMAIL"));
                usuarios.add(us);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario encontrarPorReferencia (Usuario usuario){
        String consultaTodos = "SELECT ID_USUARIO, NOME_USUARIO, NASCIMENTO," +
                " SENHA, EMAIL FROM APP_RECEITAS.USUARIO WHERE NOME_USUARIO = ? AND " +
                "SENHA = ?";
        String nomeUsuario = null;
        Usuario usuarioConsulta = new Usuario();
        try {
            PreparedStatement ps = con.prepareStatement(consultaTodos);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSenha());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarioConsulta.setId(rs.getInt("ID_USUARIO"));
                usuarioConsulta.setUsuario(rs.getString("NOME_USUARIO"));
                usuarioConsulta.setEmail(rs.getString("EMAIL"));
                Date dt = rs.getDate("NASCIMENTO");
                int ano = dt.toLocalDate().getYear();
                int mes = dt.toLocalDate().getDayOfMonth();
                int dia = dt.toLocalDate().getDayOfMonth();
                usuarioConsulta.setNascimento(ano, mes,dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioConsulta;
    }

}
