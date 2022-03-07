package dao;

import java.util.List;

public interface GenericDAO<Y> {

    void registrar(Y y);
    void atualizar(Y y);
    void deletar(Integer id);
    Y encontrarUm(Integer id);
    List<Y> encontrarTodos();
    /*
       Método que tem a intenção de retornar uma lista de um tipo T, a partir
       de alguma relação na tabela, que não seja o ID;
    */
    List<Y> encontrarPorObjeto(Object obj);
}
