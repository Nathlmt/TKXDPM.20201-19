package org.tkxdpm20201.Nhom19.persistence.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao {
    private Connection connection;
    public void connect() throws SQLException {
        this.connection = Connect.getConnection();
    }

    public ResultSet execQuery(String query) throws SQLException {
        return this.connection.createStatement().executeQuery(query);
    }

}
