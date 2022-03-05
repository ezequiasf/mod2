package utils;

public enum TipoRefeicao {

    CAFE ("Café da Manhã"),
    ALMOCO_JANTA("Almoço ou janta"),
    LANCHE("Lanche");

    private final String refeicao;

    TipoRefeicao(String refeicao) {
        this.refeicao = refeicao;
    }

    public String getRefeicao() {
        return refeicao;
    }
}
