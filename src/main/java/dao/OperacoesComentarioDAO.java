package dao;

import model.Comentario;

import java.util.List;

public class OperacoesComentarioDAO implements GenericDAO<Comentario>{

    @Override
    public void registrar(Comentario comentario) {

    }

    @Override
    public void atualizar(Comentario comentario) {

    }

    @Override
    public void deletar(Integer id) {

    }

    @Override
    public Comentario encontrarUm(Integer id) {
        return null;
    }

    @Override
    public List<Comentario> encontrarTodos() {
        return null;
    }

    @Override
    public List<Comentario> encontrarPorObjeto(Object obj) {
        return null;
    }
}
