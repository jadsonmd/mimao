package br.com.teatime.mimao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Ficha {

    @Id
    private String id;

    private String nome;
    private String tipo;
    private LocalDateTime dataInicioTreino;
    private LocalDateTime dataFimTreino;

    private List<Treino> treinos;

    public Ficha() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataInicioTreino() {
        return dataInicioTreino;
    }

    public void setDataInicioTreino(LocalDateTime dataInicioTreino) {
        this.dataInicioTreino = dataInicioTreino;
    }

    public LocalDateTime getDataFimTreino() {
        return dataFimTreino;
    }

    public void setDataFimTreino(LocalDateTime dataFimTreino) {
        this.dataFimTreino = dataFimTreino;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}