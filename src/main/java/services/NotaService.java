package services;

import model.Nota;
import repository.NotaRepository;

import java.util.List;

public class NotaService {

    private final NotaRepository notaRepo;

    public NotaService (NotaRepository notaRepo){
        this.notaRepo = notaRepo;
    }

    public void adicionarNota (Nota nota){
        notaRepo.registrar(nota);
    }

    public void atualizarNota (Integer id, Nota nota){
        notaRepo.atualizar(id, nota);
    }

    public void removerNota (Integer id){
        notaRepo.deletar(id);
    }

    public void listarNotas (){
        List<Nota> notas = notaRepo.encontrarTodos();
        notas.forEach(System.out::println);
    }
}
