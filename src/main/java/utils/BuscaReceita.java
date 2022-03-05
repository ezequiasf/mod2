package utils;

public class BuscaReceita {
///*
//    /**
//     * Este método filtra a lista atrás de apenas um ingrediente.
//     *
//     * @param ingrediente O ingrediente que se quer encontrar em qualquer refeição da lista.
//     * @param lista       A lista de refeições a pesquisar.
//     * @return Uma lista de receitas que contém o ingrediente.
//     */
//    /*
//    public static List<Receita> filtroLista(String ingrediente, List<Receita> lista) {
//        return lista.stream()
//                .filter(r -> r.impressaoIngredientes()
//                        .contains(ingrediente.replace(" ", ""))).collect(Collectors.toList());
//    }
//
//    /**
//     * Método filtra por um Tipo de Receita.
//     *
//     * @param tipo  O tipo de receita que se deseja pesquisar entre as refeições.
//     * @param lista A lista que se quer pesquisar.
//     * @return Uma lista que contenha receitas com o tipo pesquisado.
//     */
//    public static List<Receita> filtroLista(TipoReceita tipo, List<Receita> lista) {
//        return lista.stream()
//                .filter(r -> r.getTipoReceita() == tipo)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Este método procura por receitas que contenham umas série de ingredientes.
//     *
//     * @param lista A lista que se quer pesquisar.
//     * @param ing   Um array de ingredientes para procurar na lista.
//     * @return Uma lista de receitas que contenha todos os ingredientes passados.
//     */
//    public static List<Receita> filtroLista(List<Receita> lista, String... ing) {
//        List<Receita> listaFiltrada = new ArrayList<>();
//        Iterator<Receita> iterador = lista.iterator();
//        while (iterador.hasNext()) {
//            int contador = 0;
//            Receita r = iterador.next();
//            for (int i = 0; i < ing.length; i++) {
//                if (r.impressaoIngredientes().contains(ing[i])) {
//                    contador++;
//                }
//            }
//            if (contador == ing.length) {
//                listaFiltrada.add(r);
//            }
//        }
//        return listaFiltrada;
//    }
//
//    /**
//     * Este método pesquisa receitas a partir de um limite de tempo.
//     *
//     * @param tempo Limite de tempo
//     * @param lista A lista que se quer pesquisar
//     * @return A lista com receitas que contenham o limite de tempo especificado.
//     */
//    public static List<Receita> filtroLista(int tempo, List<Receita> lista) {
//        return lista.stream()
//                .filter(r -> r.getTempoPreparo() <= tempo)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Filtra a partir de um limite de preço.
//     *
//     * @param preco O limite de preço.
//     * @param lista A lista a pesquisar.
//     * @return Uma lista filtrada que tem apenas receitas com o limite de preço passado
//     * como parâmetro.
//     */
//    public static List<Receita> filtroLista(double preco, List<Receita> lista) {
//        return lista.stream()
//                .filter(r -> r.getMediaPreco() <= preco)
//                .sorted(Comparator.comparing(Receita::getMediaPreco))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Apresenta uma lista decrescente ou crescente de preços de acordo
//     * com o sinalizador passado como parâmetro.
//     *
//     * @param sinalizador Indica qual organização da lista será feita. Se for
//     *                    especificado um valor positivo, a lista se apresentará
//     *                    de maneira CRESCENTE. Caso contrário, DECRESCENTE.
//     * @param lista       A lista a pesquisar.
//     * @return Uma lista organizada a partir do sinalizador passado como argumento.
//     */
//    public static List<Receita> listaPrecos(int sinalizador, List<Receita> lista) {
//        return lista.stream()
//                .sorted((r1, r2) -> {
//                    if (sinalizador < 0) {
//                        return Double.compare(r1.getMediaPreco(), r2.getMediaPreco());
//                    }
//                    return Double.compare(r2.getMediaPreco(), r1.getMediaPreco());
//                })
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Filtra o tipo de refeição almoco da lista informada.
//     *
//     * @param lista    A lista a pesquisar.
//     * @param calorias Número total de calorias diárias que a pessoa deve consumir.
//     * @return Uma lista filtrada apenas com receitas que tenham o tipo refeição almoço.
//     */
//    public static List<Receita> filtroAlmoco(List<Receita> lista, Double calorias) {
//        return filtroRefeicao(lista, calorias, 0.3, TipoRefeicao.ALMOCO_JANTA,0.15);
//    }
//
//    /**
//     * Filtra os tipos cafe e lanche da lista passada como argumento.
//     *
//     * @param lista    Lista a pesquisar.
//     * @param calorias Número total de calorias diárias que a pessoa deve consumir.
//     * @param tipo     Serve como uma sentinela ou bandeira. Caso o valor seja 1, ele
//     *                 executará o filtro para Cafe, caso seja -1, executará para Lanche.
//     * @return Uma lista filtrada pelo tipo de refeição especificado pelo argumento tipo.
//     */
//    public static List<Receita> filtroLancheCafe(List<Receita> lista, Double calorias, int tipo) {
//
//        if (tipo == 1) {
//            return filtroRefeicao(lista, calorias, 0.2, TipoRefeicao.CAFE,0.10);
//        } else if (tipo == -1) {
//            return filtroRefeicao(lista, calorias, 0.2, TipoRefeicao.LANCHE,0.10);
//        }
//        return null;
//    }
//
//    private static List<Receita> filtroRefeicao(List<Receita> lista, Double calorias, Double porcentagem
//            , TipoRefeicao tipoRefeicao, Double porcentagemDelta) {
//
//        return lista.stream().filter(r -> {
//            if (r.getTipoRefeicao() == tipoRefeicao) {
//                return (r.getCalorias() > calorias * porcentagemDelta) && (r.getCalorias() <= calorias * porcentagem);
//            }
//            return false;
//        }).collect(Collectors.toList());
//    }
//
//    /**
//     * Método que escolhe aleatoriamente um tipo de refeição da lista passada.
//     *
//     * @param lista    A lista que se deseja captar os tipos de refeição aleatórios.
//     * @param calorias O total de calorias que a pessoa deve consumir.
//     * @return Uma lista com 4 receitas com tipos de refeição diferentes.
//     */
//    public static List<Receita> cardapioDoDia(List<Receita> lista, Double calorias) {
//
//        Random random = new Random();
//        List<Receita> cafes = filtroLancheCafe(lista, calorias, 1);
//        Receita cafe = null;
//        if (!(cafes.size()<=0)){
//            cafe = cafes.get(random.nextInt(cafes.size()));
//        }
//
//        List<Receita> lanches = filtroLancheCafe(lista, calorias, -1);
//        Receita lanche = null;
//        if (!(lanches.size()<=0)){
//            lanche = lanches.get(random.nextInt(lanches.size()));
//        }
//
//        List<Receita> jantas = filtroAlmoco(lista, calorias);
//        Receita janta = null;
//        if (!(jantas.size()<=0)){
//            janta = jantas.get(random.nextInt(jantas.size()));
//        }
//
//        List<Receita> almocos = filtroAlmoco(lista, calorias);
//        Receita almoco = null;
//        if (!(almocos.size()<=0)){
//           almoco = almocos.get(random.nextInt(almocos.size()));
//        }
//        return new ArrayList<>(Arrays.asList(cafe, almoco, lanche, janta));
//    }
//
//    /**
//     * Calcula a soma de todas as calorias das receitas da lista.
//     *
//     * @param receitas A lista de receitas que se deseja calcular.
//     * @return A soma de todas as calorias.
//     */
//    public static Double totalCalorias (List<Receita> receitas){
//        return receitas.stream().mapToDouble(r-> {
//            if(r!=null){
//               return r.getCalorias();
//            }
//            return 0.0;
//        }).sum();
//    }
}
