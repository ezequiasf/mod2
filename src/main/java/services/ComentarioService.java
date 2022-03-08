package services;

import model.Comentario;
import repository.ComentarioRepository;

import java.util.List;

public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService (ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

    public void adicionarComentario (Comentario coment){
        comentarioRepository.registrar(coment);
    }

    public void atualizarComentario (Integer id, Comentario coment){
        comentarioRepository.atualizar(id, coment);
    }

    public void removerComentario (Integer id){
        comentarioRepository.deletar(id);
    }

    public void listarComentarios (){
        List<Comentario> comentarios = comentarioRepository.encontrarTodos();
        comentarios.forEach(System.out::println);
    }
}
