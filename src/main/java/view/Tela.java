package view;

import model.Ingrediente;
import model.Receita;
import model.Usuario;
import services.*;
import utils.BuildService;
import utils.TipoReceita;
import utils.TipoRefeicao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tela {

    private final static Scanner scanner = new Scanner(System.in);
    private final static ReceitaService receitaSvc = BuildService.buildReceitaService();
    private final static UsuarioService usuarioSvc = BuildService.buildUsuarioService();
    private final static NotaService notaSvc = BuildService.buildNotaService();
    private final static IngredienteService ingSvc = BuildService.buildIngredienteService();
    private final static ComentarioService comentSvc = BuildService.buildComentarioService();

    public static void telaPrincipal (){
        int respostaInicial = 0;

        do {
            switch (opcoesIniciais()) {
                case 1 -> viewListaTodasReceitas();
                case 2 -> viewFiltros();
                case 3 -> viewTuaSaude();
                case 4 -> {
                    if(viewLogin()==1){
                        respostaInicial = 6;
                    }
                }
                case 5 -> viewCadastroPlataforma();
                case 6 -> {
                    System.out.println("Bon Apetit! Nos vemos na churrascada...");
                    respostaInicial = 6;
                }
                default -> System.out.println("Opa! Opção inválida, escolha uma das opções informadas, por gentileza.");
            }
        } while (respostaInicial != 6);
    }

    public static void ambienteLogin (Usuario usuario){
        int respostaInicial = 0;

        do {
            switch (opcoesCadastrado()) {
                case 1 -> viewListaTodasReceitasCadastrado();
                case 2 -> viewCadastroReceita(usuario);
                case 3 -> viewAtualizarReceita();
                case 4 -> viewDeletarReceitas();
                case 5 -> viewMinhasReceitas();
                case 6 -> viewFiltros();
                case 7 -> viewTuaSaude();
                case 8 -> {
                    System.out.println("Bon Apetit! Nos vemos na churrascada...");
                    respostaInicial = 6;
                }
                default -> System.out.println("Opa! Opção inválida, escolha uma das opções informadas, por gentileza.");
            }
        } while (respostaInicial != 6);
    }


    private static int opcoesIniciais()  {
        viewLogo();
        System.out.println("""
                O que deseja fazer agora?
                [1] - Listar Receitas do site
                [2] - Filtros de Receita
                [3] - Tua Saúde
                [4] - Login
                [5] - Não é cadastrado? Cadastre-se grátis!
                [6] - Sair
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine(); //Flush
        return resposta;
    }

    private static int opcoesCadastrado()  {
        viewLogo();
        System.out.println("""
                O que deseja fazer agora?
                [1] - Listar Receitas do site
                [2] - Cadastrar receita
                [3] - Atualizar receita
                [4] - Deletar receita
                [5] - Minhas receitas
                [6] - Filtros de receita
                [7] - Tua saúde
                [8] - Sair
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine(); //Flush
        return resposta;
    }

    private static void viewLogo() {
        System.out.printf("""
                ************************************************************%nO que dá para fazer? - Receitas \u00A9 By Ezequias B., Pablo K.%n************************************************************
                """);
    }

    public static void viewListaTodasReceitas (){
        receitaSvc.listarReceitas();
    }

    public static void viewListaTodasReceitasCadastrado (){
        receitaSvc.listarReceitas();
        System.out.println("""
                Deseja comentar ou dar uma nota a alguma receita?
                [1] - Comentar
                [2] - Dar uma nota
                [3] - Não
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine();//Flush
        if (resposta == 1){
            viewPersonalizadaId();
        }
    }

    public static int viewLogin (){
        int sinalizador = 0;
        System.out.println("Nome de usuário:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Senha:");
        String senhaUsuario = scanner.nextLine();
        Usuario usuario = new Usuario();
        usuario.setUsuario(nomeUsuario);
        usuario.setSenha(senhaUsuario);
        Usuario usuarioConsulta = usuarioSvc.encontrarPorReferencia(usuario);
        if(usuarioConsulta.getUsuario()!=null){
            ambienteLogin(usuarioConsulta);
            sinalizador = 1;
        }else{
            System.out.println("Você ainda não está cadastrado ou digitou os dados errado.");
        }
        return sinalizador;
    }

    public static void viewFiltros (){

    }

    public static void viewTuaSaude (){

    }

    public static void viewCadastroPlataforma (){
        System.out.println("Digite um nome para usuário:");
        String novoNome = scanner.nextLine();

        System.out.println("Agora digite uma senha:");
        String novaSenha = scanner.nextLine();

        System.out.println("Data de nascimento: ano/mes/dia");
        String data = scanner.nextLine();
        Integer ano = Integer.valueOf(data.split("/")[0]);
        Integer mes = Integer.valueOf(data.split("/")[1]);
        Integer dia = Integer.valueOf(data.split("/")[2]);

        System.out.println("Email:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario();
        usuario.setUsuario(novoNome);
        usuario.setSenha(novaSenha);
        usuario.setNascimento(ano,mes,dia);
        usuario.setEmail(email);
        usuarioSvc.adicionarUsuario(usuario);
    }

    public static void viewCadastroReceita (Usuario usuario){
        System.out.println("Qual é o nome da receita?");
        String nomeReceita = scanner.nextLine();

        TipoReceita tipoDaReceita;
        TipoRefeicao tipoRefeicao;
        int tempoReceita;

        System.out.println("Em qual opção sua receita se encaixa melhor?\n [DIET] | " +
                "[VEGANA] | [VEGETARIANA] | [SALGADA] | [DOCE] | [SEM_GLUTEN] |" +
                " [ZERO_LACTOSE]");
        tipoDaReceita = TipoReceita.valueOf(scanner.nextLine().replace(" ", "").toUpperCase());

        System.out.println("Qual é o tipo de refeição?\n [CAFE] | " +
                "[ALMOCO] | [LANCHE] | [JANTA]");
        String respostaRefeicao = scanner.nextLine();
        if (respostaRefeicao.equalsIgnoreCase("Almoco") || respostaRefeicao.equalsIgnoreCase("Janta")) {
            respostaRefeicao = "ALMOCO_JANTA";
        }
        tipoRefeicao = TipoRefeicao.valueOf(respostaRefeicao.replace(" ", "").toUpperCase());


        System.out.println("Quanto tempo em média leva para fazer? Digite um número em min");
        tempoReceita = scanner.nextInt();
        scanner.nextLine(); //Flush

    System.out.println("Agora vamos aos ingredientes: Digite no seguinte formato:\n" +
            "nomeIngrediente1,quantidade medida/nomeIngrediente2,quantidade medida/...");
    String ingredientesJuntos = scanner.nextLine();
    List<Ingrediente> listaIngredientes = new ArrayList<>();
    String[] primeiraSeparacao = ingredientesJuntos.replace(" ", "").split("/");
    for (String str : primeiraSeparacao) {
        Ingrediente ing = new Ingrediente();
        ing.setNome(str.split(",")[0]);
        ing.setQuantidade(str.split(",")[1]);
        listaIngredientes.add(ing);
    }

        System.out.println("Quantas calorias tem em média essa refeição?");
        double qtdCalorias = scanner.nextDouble();
        scanner.nextLine(); //Flush

        System.out.println("Ok. Você sabe quanto custa em média? Digite [n], Caso não saiba.");
        String preco = scanner.nextLine();
        double mediaPreco;
        if (!preco.equalsIgnoreCase("n")) {
            try {
                mediaPreco = Double.parseDouble(preco);
            } catch (NumberFormatException nex) {
                mediaPreco = 0.0;
            }
        } else {
            mediaPreco = 0.0;
        }

        System.out.println("Já estamos quase lá. Descreva sucintamente como fazer a receita:");
        String descricao = scanner.nextLine();

        Receita receita = new Receita ();
        receita.setMediaNota(0.0);
        receita.setId_usuario(usuario.getId());
        receita.setTipoReceita(tipoDaReceita);
        receita.setModoPreparo(descricao);
        receita.setTempoPreparo(tempoReceita);
        receita.setTipoRefeicao(tipoRefeicao);
        receita.setNomeReceita(nomeReceita);
        receita.setCalorias(qtdCalorias);
        receita.setMediaPreco(mediaPreco);
        Receita receitaComId = receitaSvc.adicionarReceita(receita);
        listaIngredientes.forEach(ing ->{
            ing.setId_receita(receitaComId.getId_receita());
            ingSvc.adicionarIngrediente(ing);
        });
    }

    public static void viewAtualizarReceita (){

    }

    public static void viewDeletarReceitas (){

    }

    public static int viewPersonalizadaId (){
        return 2;
    }

    public static void viewMinhasReceitas (){
        System.out.println("Aqui estão suas receitas cadastradas:");

    }

}
