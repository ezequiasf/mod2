package utils;

public class CalculoEnergeticoAtividade extends CalculoEnergetico {

    private double fatorAtividadeFisica;

    public CalculoEnergeticoAtividade (String ocorrenciaAtividade){
        if(ocorrenciaAtividade.equalsIgnoreCase("leve")){
            fatorAtividadeFisica = 1.55;
        }else if(ocorrenciaAtividade.equalsIgnoreCase("moderada")){
            fatorAtividadeFisica = 1.85;
        }else if(ocorrenciaAtividade.equalsIgnoreCase("intensa")){
            fatorAtividadeFisica = 2.20;
        }
    }

    @Override
    public Double calculoGastoEnergetico (Double altura, Double peso, String sexo,int idade){
       return super.calculoGastoEnergetico(altura,peso,sexo,idade)*fatorAtividadeFisica;
    }
}
