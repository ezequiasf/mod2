package repository;

import model.Receita;

import java.util.List;

public class ReceitaRepository implements GenericRepository<Receita> {

    @Override
    public Receita registrar(Receita receita) {
        return null;
    }

    @Override
    public void atualizar(Integer id, Receita receita) {

    }

    @Override
    public void deletar(Integer id) {

    }

    @Override
    public Receita encontrarUm(Integer id) {
        return null;
    }

    @Override
    public List<Receita> encontrarTodos() {
        return null;
    }

    @Override
    public List<Receita> encontrarPorObjeto(Object obj) {
        return null;
    }
}
