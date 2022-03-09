package repository;

import java.util.List;

public interface GenericRepository<Y> {
    Y registrar(Y y);

    void atualizar(Integer id, Y y);

    void deletar(Integer id);

    Y encontrarUm(Integer id);

    List<Y> encontrarTodos();
}
