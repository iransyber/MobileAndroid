package com.example.iran.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contas")
public class Contas {

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private int userId;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private int tipo;
    @DatabaseField
    private String numeroConta;
    @DatabaseField
    private float saldo;
    @DatabaseField
    private boolean ativo;

    @DatabaseField(foreign = true)
    private User user;

    public Contas(){}
    public Contas(int userId, String descricao, int tipo, String numeroConta, float saldo, boolean ativo){
        this.userId = userId;
        this.descricao = descricao;
        this.tipo = tipo;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.ativo = ativo;
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

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
