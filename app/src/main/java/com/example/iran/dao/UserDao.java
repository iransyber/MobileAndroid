package com.example.iran.dao;


import com.example.iran.models.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class UserDao extends BaseDaoImpl<User, Integer> {
    public UserDao(ConnectionSource cs) throws SQLException {
        super(User.class);
        setConnectionSource(cs);
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
