package utils;

@FunctionalInterface
public interface Calculo {
    /**
     * Calculo de gasto energetico.
     *
     * @param altura A altura da pessoa analisada.
     * @param peso   O peso da pessoa analisada.
     * @param sexo   O sexo da pessoa analisada.
     * @param idade  A idade da pessoa analisada.
     * @return O total de calorias diárias que a pessoa analisada deve consumir.
     */
    Double calculoGastoEnergetico(Double altura, Double peso, String sexo
            , int idade);
}
