package dao;

import model.Usuario;

import java.util.List;

public class OperacoesUsuarioDAO implements GenericDAO<Usuario>{

    @Override
    public void registrar(Usuario usuario) {

    }

    @Override
    public void atualizar(Usuario usuario) {

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
