package org.tkxdpm20201.Nhom19.persistence.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao {
    static final String HOST = "jdbc:postgresql://35.192.204.170:5432/tkxdpm";
    static final String USER = "postgres";
    static final String PASS = "letuan123";

    void connect();
    ResultSet execQuery(String query) throws SQLException;
}
