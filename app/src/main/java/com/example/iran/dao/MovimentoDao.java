package com.example.iran.dao;

import com.example.iran.models.Movimento;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class MovimentoDao extends BaseDaoImpl<Movimento, Integer> {
    public MovimentoDao(ConnectionSource cs) throws SQLException {
        super(Movimento.class);
        setConnectionSource(cs);
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
