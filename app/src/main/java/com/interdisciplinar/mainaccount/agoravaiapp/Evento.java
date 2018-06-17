package com.interdisciplinar.mainaccount.agoravaiapp;

public class Evento {


    private String titulo;
    private String categoria;
    private String descricao;

    public Evento() {
    }

    public Evento(String titulo, String categoria, String descricao) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
