package com.example.iran.models;

public class Moedas {
    private String nome;
    private Float valor;
    private String fonte;

    @Override
    public String toString() {
        return " Moeda: " + nome +
                "\r\n Valor: " + valor.toString() +
                "\r\n Fonte: " + fonte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}
