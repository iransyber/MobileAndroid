package com.example.iran.models;

import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.Collection;

@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String email;
    @DatabaseField
    private int nivel;
    @DatabaseField
    private String senha;
    @ForeignCollectionField
    private Collection<Contas> contas;


    public User(){}
    public User (int _id, String nome, String email, int nivel, String senha){
        this._id = _id;
        this.nome = nome;
        this.email = email;
        this.nivel = nivel;
        this.senha = senha;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
