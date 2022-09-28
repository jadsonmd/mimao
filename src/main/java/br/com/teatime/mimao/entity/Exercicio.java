package br.com.teatime.mimao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Exercicio {

    @Id
    private String id;
    private String nome;
    private String finalidade;
    private String image;
    private String video;
    private String descricao;
    private String biset;
    private Boolean pausaDescanso;
    private String serie;
    private String repeticao;

    public Exercicio() { }

    public Exercicio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBiset() {
        return biset;
    }

    public void setBiset(String biset) {
        this.biset = biset;
    }

    public Boolean getPausaDescanso() {
        return pausaDescanso;
    }

    public void setPausaDescanso(Boolean pausaDescanso) {
        this.pausaDescanso = pausaDescanso;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}