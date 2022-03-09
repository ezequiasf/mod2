package view;

import model.*;
import services.*;
import utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Tela {

    private final static Scanner scanner = new Scanner(System.in);
    private final static BuscaReceita busca = new BuscaReceita(BuildService.getConnection());
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
                case 1 -> viewListaTodasReceitasCadastrado(usuario);
                case 2 -> viewCadastroReceita(usuario);
                case 3 -> viewAtualizarReceita(usuario);
                case 4 -> viewDeletarReceitas(usuario);
                case 5 -> viewMinhasReceitas(usuario);
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

    public static void viewListaTodasReceitasCadastrado (Usuario usuario){
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
            int idReceita = viewPersonalizadaId(usuario);
            System.out.println("Adicione o comentário:");
            String comentario = scanner.nextLine();
            Comentario coment = new Comentario();
            coment.setId_receita(idReceita);
            coment.setId_usuario(usuario.getId());
            coment.setComentario(comentario);
            comentSvc.adicionarComentario(coment);
            System.out.println("Comentário adicionado.");
        }
        else if (resposta ==2){
            int idReceita = viewPersonalizadaId(usuario);
            System.out.println("Dê uma nota de 0 a 5:");
            double nota = scanner.nextDouble();
            Nota notaObj = new Nota();
            notaObj.setId_receita(idReceita);
            notaObj.setId_usuario(usuario.getId());
            notaObj.setNota(nota);
            notaSvc.adicionarNota(notaObj);
            System.out.println("Nota adicionada.");
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
        List<Object[]> receitas;
        System.out.print("""
                Como você gostaria de pesquisar as receitas?
                [1] - Ingredientes
                [2] - Tempo de preparo
                [3] - Preço
                [4] - Tipo de receita
                """);
        int resposta = scanner.nextInt();
        scanner.nextLine(); //Flush

        switch (resposta) {
            case 1 -> {
                System.out.println("Digite o ingrediente que você tem:");
                String ingrediente = scanner.nextLine();
                receitas = busca.consulta(ingrediente, 4);
                apresentador(receitas);
            }
            case 2 -> {
                System.out.println("Quanto tempo você tem para preparar a receita? (Em minutos)");
                int tempoReceita = scanner.nextInt();
                receitas = busca.consulta(tempoReceita, 3);
                apresentador(receitas);
            }
            case 3 -> {
                System.out.println("Escolha uma forma de filtro > [1] - Limite de preço |" +
                        " [2] - Da receita barata para mais cara | [3] - Da cara para mais barata");
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Até quanto você pode gastar?");
                        double valor = scanner.nextDouble();
                        receitas = busca.consulta(valor, 0);
                        apresentador(receitas);
                    }
                    case 2 -> apresentador(busca.listaPrecosCrescente());
                    case 3 -> apresentador(busca.listaPrecosDecrescente());
                }
            }
            case 4 -> {
                System.out.println("Em qual opção sua receita se encaixa melhor?\n [DIET] | " +
                        "[VEGANA] | [VEGETARIANA] | [SALGADA] | [DOCE] | [SEM_GLUTEN] |" +
                        " [ZERO_LACTOSE]");
                apresentador(busca.consulta(TipoReceita.valueOf(scanner.nextLine().replace(" ", "")
                        .toUpperCase()),6));
            }
        }
        if ((resposta <= 4) && (resposta > 0)) {
            System.out.println("Aqui estão as opções filtradas! ;)");
        }
    }

    public static void viewTuaSaude (){
        System.out.println("A partir desta funcionalidade,você poderá escolher uma dieta adequada" +
                " as suas necessidades :)");
        System.out.println("Vamos as informações necessárias:");

        System.out.println("Qual é o seu sexo? [M] | [F]");
        String sexo = scanner.nextLine();

        System.out.println("Qual é a sua idade?");
        int idade = scanner.nextInt();

        System.out.println("Agora digite o seu peso:");
        double peso = scanner.nextDouble();

        System.out.println("ok, informe sua altura:");
        double altura = scanner.nextDouble();

        System.out.println("Você costuma fazer atividades físicas? 1 - [Sim] / 2 - [Não]");
        int respostaAtvFisica = scanner.nextInt();
        scanner.nextLine(); //Flush
        double calorias;
        if (respostaAtvFisica == 1) {
            System.out.println("Como costuma ser a ocorrência das atividades?  [LEVE] | " +
                    "[MODERADA] |  [INTENSO]");
            String ocorrenciaAtividades = scanner.nextLine();
            CalculoEnergeticoAtividade calculoEnergeticoAtv = new CalculoEnergeticoAtividade(ocorrenciaAtividades);
            calorias = calculoEnergeticoAtv.calculoGastoEnergetico(altura, peso, sexo, idade);
        } else {
            CalculoEnergetico calculoEnergetico = new CalculoEnergetico();
            calorias = calculoEnergetico.calculoGastoEnergetico(altura, peso, sexo, idade);
        }
        System.out.println("Você deseja visualizar uma lista ou um " +
                "cardápio do dia personalizado? 1 - Lista / 2 - Cardápio do dia");
        int respostaCardapio = scanner.nextInt();
       if (respostaCardapio == 1) {
            System.out.println("===================== Opções de Café =======================");
            apresentador(busca.consulta(TipoRefeicao.CAFE, 7));
            System.out.println("===================== Opções de Refeição (Almoço e Janta) =======================");
            apresentador(busca.consulta(TipoRefeicao.ALMOCO_JANTA, 7));
            System.out.println("===================== Opções de Lanche =======================");
           apresentador(busca.consulta(TipoRefeicao.LANCHE, 7));
        } else {
           System.out.println("================ Cardápio do dia ======================");

            System.out.println("********* Café da manha *********");
            apresentaAleatorio(busca.consulta(5,TipoRefeicao.CAFE, 0.1*calorias, 0.2*calorias));

            System.out.println("********* Almoço *********");
            apresentaAleatorio(busca.consulta(5,TipoRefeicao.ALMOCO_JANTA,
                    0.15*calorias, 0.3*calorias));

            System.out.println("********* Lanche *********");
            apresentaAleatorio(busca.consulta(5,TipoRefeicao.LANCHE, 0.1*calorias, 0.2*calorias));

            System.out.println("********* Janta *********");
            apresentaAleatorio(busca.consulta(5,TipoRefeicao.ALMOCO_JANTA, 0.15*calorias, 0.3*calorias));
        }
    }

    public static void viewCadastroPlataforma (){
        System.out.println("Digite um nome para usuário:");
        String novoNome = scanner.nextLine();

        System.out.println("Agora digite uma senha:");
        String novaSenha = scanner.nextLine();

        System.out.println("Data de nascimento: ano/mes/dia");
        String data = scanner.nextLine();
        int ano = Integer.parseInt(data.split("/")[0]);
        int mes = Integer.parseInt(data.split("/")[1]);
        int dia = Integer.parseInt(data.split("/")[2]);

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
        List<Object> info  = viewAtualizaECadastraReceita(usuario);
        Receita receita = (Receita) info.get(0);
        List<Ingrediente> listaIngredientes = (List<Ingrediente>) info.get(1);

        Receita receitaComId = receitaSvc.adicionarReceita(receita);
        listaIngredientes.forEach(ing ->{
            ing.setId_receita(receitaComId.getId_receita());
            ingSvc.adicionarIngrediente(ing);
        });
    }

    public static List<Object> viewAtualizaECadastraReceita (Usuario usuario){
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
        List<Object> info = new ArrayList<>();
        info.add(receita);
        info.add(listaIngredientes);
        return info;
    }

    public static void viewAtualizarReceita (Usuario usuario){
        int idAntigoReceita = viewPersonalizadaId(usuario);
        List<Object> info = viewAtualizaECadastraReceita(usuario);
        Receita novoR = (Receita) info.get(0);
        List<Ingrediente> novosIngs = (List<Ingrediente>) info.get(1);
        Ingrediente ingAntigo = new Ingrediente();
        ingAntigo.setId_receita(idAntigoReceita);

        List<Integer> idsIngAntigos = ingSvc.encontrarPorReferencia(ingAntigo);
        receitaSvc.atualizarReceita(idAntigoReceita, novoR);

        novosIngs.forEach(ing ->{
            ing.setId_receita(idAntigoReceita);
            ingSvc.adicionarIngrediente(ing);
        });
        idsIngAntigos.forEach(ingSvc::removerIngrediente);
        System.out.println("Receita atualizada com sucesso.");
    }

    public static void viewDeletarReceitas (Usuario usuario){
        int idReceitaAntiga = viewPersonalizadaId(usuario);

        Ingrediente ingAntigo = new Ingrediente();
        ingAntigo.setId_receita(idReceitaAntiga);

        List<Integer> idsAntigosIngredientes = ingSvc.encontrarPorReferencia(ingAntigo);
        idsAntigosIngredientes.forEach(ingSvc::removerIngrediente);

        receitaSvc.removerReceita(idReceitaAntiga);
        System.out.println("Receita deletada com sucesso.");
    }

    public static int viewPersonalizadaId (Usuario usuario){
        System.out.println("Informe o id da receita:");
        busca.consulta(usuario.getId(), 9).stream().map(arr-> (Receita) arr[0]).distinct()
                .forEach(r-> System.out.println(r.getId_receita()+" - "+r.getNomeReceita()));
        int resposta = scanner.nextInt();
        scanner.nextLine(); //flush
        return resposta;
    }

    public static void viewMinhasReceitas (Usuario usuario){
        System.out.println("Aqui estão suas receitas cadastradas:");
        apresentador(busca.consulta(usuario.getId(), 9));
    }

    public static void apresentador (List<Object[]> lista){
       List<Receita> receitas = lista.stream().map(arr-> (Receita) arr[0]).distinct().collect(Collectors.toList());
       receitas.forEach(System.out::println);
    }

    public static void apresentaAleatorio (List<Object[]> lista){
        Random rd = new Random();
        if (lista.size()>0){
            List<Object[]> refeicaoEscolhida = new ArrayList<>();
            refeicaoEscolhida.add(lista.get(rd.nextInt(lista.size())));
            apresentador(refeicaoEscolhida);
        }
    }


}
