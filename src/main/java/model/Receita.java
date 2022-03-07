package model;

import utils.TipoReceita;
import utils.TipoRefeicao;

public class Receita {

    private Integer id_receita;
    private Integer id_usuario;
    private String nomeReceita;
    private TipoReceita tipoReceita;
    private TipoRefeicao tipoRefeicao;
    private String modoPreparo;
    private Integer tempoPreparo;
    private Double mediaPreco;
    private Double calorias;

    public Receita (){}
    public Receita(Integer id_usuario, String nomeReceita, TipoReceita tipoReceita, TipoRefeicao tipoRefeicao, Double calorias, Double mediaPreco,
                   String modoPreparo, Integer tempoPreparo) {
        this.id_usuario = id_usuario;
        this.nomeReceita = nomeReceita;
        this.tipoReceita = tipoReceita;
        this.tipoRefeicao = tipoRefeicao;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.mediaPreco = mediaPreco;
        this.calorias = calorias;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_receita() {
        return id_receita;
    }

    public void setId_receita(Integer id_receita) {
        this.id_receita = id_receita;
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public Integer getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Integer tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Double getMediaPreco() {
        return mediaPreco;
    }

    public void setMediaPreco(Double mediaPreco) {
        this.mediaPreco = mediaPreco;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }
}
