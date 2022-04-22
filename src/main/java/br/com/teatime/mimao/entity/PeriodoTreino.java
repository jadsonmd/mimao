package br.com.teatime.mimao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class PeriodoTreino {

    @Id
    private String id;
    private String nome;
    private String tipo;
    private LocalDate dataInicioTreino;

    private List<Treino> treinos;

    public PeriodoTreino(String nome) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public LocalDate getDataInicioTreino() {
        return dataInicioTreino;
    }

    public void setDataInicioTreino(LocalDate dataInicioTreino) {
        this.dataInicioTreino = dataInicioTreino;
    }
}