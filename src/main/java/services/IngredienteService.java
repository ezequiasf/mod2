package services;

import model.Ingrediente;
import repository.IngredienteRepository;

import java.util.List;

public class IngredienteService {

    private final IngredienteRepository ingRepo;

    public IngredienteService (IngredienteRepository ingRepo){
        this.ingRepo = ingRepo;
    }

    public void adicionarIngrediente (Ingrediente ingrediente){
        ingRepo.registrar(ingrediente);
    }

    public void atualizarIngrediente (Integer id, Ingrediente ingrediente){
        ingRepo.atualizar(id, ingrediente);
    }

    public void removerIngrediente (Integer id){
        ingRepo.deletar(id);
    }

    public void listarIngredientes (){
        List<Ingrediente> ingredientes = ingRepo.encontrarTodos();
        ingredientes.forEach(System.out::println);
    }
}