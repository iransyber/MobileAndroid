package com.example.iran.dao;


import com.example.iran.models.TipoConta;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class TipoContaDao extends BaseDaoImpl<TipoConta, Integer> {
    public TipoContaDao(ConnectionSource cs) throws SQLException {
        super(TipoConta.class);
        setConnectionSource(cs);
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
