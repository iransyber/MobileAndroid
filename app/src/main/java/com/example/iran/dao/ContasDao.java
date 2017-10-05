package com.example.iran.dao;


import com.example.iran.models.Contas;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ContasDao extends BaseDaoImpl<Contas, Integer> {
    public ContasDao(ConnectionSource cs) throws SQLException {
        super(Contas.class);
        setConnectionSource(cs);
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
