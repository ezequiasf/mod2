package repository;

import model.Nota;

import java.util.List;

public class NotaRepository implements GenericRepository<Nota> {

    @Override
    public Nota registrar(Nota nota) {
        return null;
    }

    @Override
    public void atualizar(Integer id, Nota nota) {

    }

    @Override
    public void deletar(Integer id) {

    }

    @Override
    public Nota encontrarUm(Integer id) {
        return null;
    }

    @Override
    public List<Nota> encontrarTodos() {
        return null;
    }

    @Override
    public List<Nota> encontrarPorObjeto(Object obj) {
        return null;
    }
}
