package services;

import model.Usuario;
import repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuRepo;

    public UsuarioService(UsuarioRepository usuRepo) {
        this.usuRepo = usuRepo;
    }

    public Usuario adicionarUsuario(Usuario usuario) {
        return usuRepo.registrar(usuario);
    }

    public void atualizarUsuario(Integer id, Usuario usuario) {
        usuRepo.atualizar(id, usuario);
    }

    public void removerUsuario(Integer id) {
        usuRepo.deletar(id);
    }

    public void listarPessoa() {
        List<Usuario> usuarios = usuRepo.encontrarTodos();
        usuarios.forEach(System.out::println);
    }

    public Usuario encontrarPorReferencia(Usuario usuario) {
        return usuRepo.encontrarPorReferencia(usuario);
    }

}
