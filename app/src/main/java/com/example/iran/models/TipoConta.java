package com.example.iran.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tipoconta")
public class TipoConta {

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private boolean ativo;

    @DatabaseField(foreign = true)
    private Contas contas;

    public TipoConta(){}
    public TipoConta(String descricao, boolean ativo){
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
