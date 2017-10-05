package com.example.iran.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.iran.models.Contas;
import com.example.iran.models.TipoConta;
import com.example.iran.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String databaseName = "controlefinanceiro.db";
    private static final int databaseVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, User.class);
            TableUtils.createTable(cs, Contas.class);
            TableUtils.createTable(cs, TipoConta.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, User.class, true);
            TableUtils.dropTable(cs, Contas.class, true);
            TableUtils.dropTable(cs, TipoConta.class, true);

            onCreate(sd, cs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        super.close();
    }
}
