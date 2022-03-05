package utils;

/**
 * Esta classe implementa a lógica da fórmula mais básica
 * provida pela OMS , a partir de estudos de 2004.
 */
public class CalculoEnergetico implements Calculo {
    @Override
    public Double calculoGastoEnergetico(Double altura, Double peso, String sexo
            , int idade) {
          double multiplicador;
          double numero;

        if(sexo.equalsIgnoreCase("M")){
            if (idade<3){
                multiplicador = 59.512;
                numero = -30.4;
            }else if (idade <10){
                multiplicador = 22.706;
                numero = 504.3;
            }else if (idade <17){
                multiplicador = 17.686;
                numero = 658.2;
            }else if (idade <30){
                multiplicador = 15.057;
                numero = 692.2;
            }else if (idade<60){
                multiplicador = 11.472;
                numero = 873.1;
            }else{
                multiplicador = 11.711;
                numero = 587.7;
            }
        }else {
            if (idade<3){
                multiplicador = 58.317;
                numero = 31.1;
            }else if (idade <10){
                multiplicador = 20.315;
                numero = 485.9;
            }else if (idade <17){
                multiplicador = 13.384;
                numero = 692.6;
            }else if (idade <30){
                multiplicador = 14.818;
                numero = 486.6;
            }else if (idade<60){
                multiplicador = 8.126;
                numero = 845.6;
            }else{
                multiplicador = 9.082;
                numero = 658.5;
            }
        }
        return  ((multiplicador * peso) + numero);
    }

}
