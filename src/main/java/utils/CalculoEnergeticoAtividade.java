package utils;

/**
 *  Esta classe implementa a lógica da fórmula mais básica com
 *  o uso adicional do fator de atividade física
 *  provida pela OMS, a partir de estudos de 2004.
 */
public class CalculoEnergeticoAtividade extends CalculoEnergetico {

    private double fatorAtividadeFisica;

    /**
     * Instancia uma nova Calculo energetico atividade.
     *
     * @param ocorrenciaAtividade Recebe a ocorrência de atividades que a pessoa
     *                            analisada tem.
     */
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
