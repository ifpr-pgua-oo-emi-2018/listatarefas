package model;

import java.time.LocalDateTime;

public class Tarefa {

    private String titulo;
    private String descricao;
    private LocalDateTime prazo;
    private boolean concluida;

    public Tarefa(String titulo, String descricao, LocalDateTime prazo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String toString(){
        return this.titulo;
    }
}
