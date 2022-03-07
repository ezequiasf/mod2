package repository;

import model.Comentario;

import java.util.List;

public class ComentarioRepository implements GenericRepository<Comentario> {

    @Override
    public Comentario registrar(Comentario comentario) {
        return null;
    }

    @Override
    public void atualizar(Integer id, Comentario comentario) {

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
