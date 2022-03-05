package dao;

import java.util.List;

public interface OperacoesDAO<T> {
    Boolean cadastro (T instancia);
    Boolean atualizar (Integer i, T instancia);
    List<T> lerTodos ();
    Boolean remover (Integer id);
}
