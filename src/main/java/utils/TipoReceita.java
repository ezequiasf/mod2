package utils;

public enum TipoReceita {
    VEGANA("Vegana"),
    DIET("Diet"),
    VEGETARIANA("Vegetariana"),
    DOCE("Doce"),
    SALGADA("Salgada"),
    SEM_GLUTEN("Sem glúten"),
    ZERO_LACTOSE("Zero lactose");

    private final String tipo;

    TipoReceita(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
