package utils;

@FunctionalInterface
public interface Calculo {
    Double calculoGastoEnergetico(Double altura, Double peso, String sexo
            , int idade);
}
