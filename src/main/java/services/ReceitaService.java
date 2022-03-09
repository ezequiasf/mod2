package services;

import model.Receita;
import model.Usuario;
import repository.ReceitaRepository;

import java.util.List;

public class ReceitaService {

    private final ReceitaRepository receitaRepo;

    public ReceitaService (ReceitaRepository receitaRepo){
        this.receitaRepo = receitaRepo;
    }

    public Receita adicionarReceita (Receita receita){
        return receitaRepo.registrar(receita);
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

    public Receita encontrarPorReferencia (Receita receita){
        return receitaRepo.encontrarPorReferencia(receita);
    }
}
