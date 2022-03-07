package dao;

import model.Receita;

import java.util.List;

public class OperacoesReceitaDAO implements GenericDAO<Receita>{

    @Override
    public void registrar(Receita receita) {

    }

    @Override
    public void atualizar(Receita receita) {

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
