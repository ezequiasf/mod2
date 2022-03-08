package services;

import model.Receita;
import repository.ReceitaRepository;
import repository.UsuarioRepository;

import java.util.List;

public class ReceitaService {

    private final ReceitaRepository receitaRepo;

    public ReceitaService (ReceitaRepository receitaRepo){
        this.receitaRepo = receitaRepo;
    }

    public void adicionarReceita (Receita receita){
        receitaRepo.registrar(receita);
    }

    public void atualizarReceita (Integer id, Receita receita){
        receitaRepo.atualizar(id, receita);
    }

    public void removerReceita (Integer id){
        receitaRepo.deletar(id);
    }

    public void listarReceitas (){
        List<Receita> receitas = receitaRepo.encontrarTodos();
        receitas.forEach(System.out::println);
    }
}
