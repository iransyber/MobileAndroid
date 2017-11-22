package com.example.iran.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Iran on 21/11/2017.
 */

@DatabaseTable(tableName = "movimento")
public class Movimento {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private int userId;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private int tipo;
    @DatabaseField
    private float valor;

    @Override
    public String toString() {
        String tp;
        if (getTipo() == 0)
          tp = "Entrada";
        else
          tp = "Saida";

        return " Tipo: " +  tp +
                "\r\n Descrição: " + descricao+
                "\r\n Valor: " + valor;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
