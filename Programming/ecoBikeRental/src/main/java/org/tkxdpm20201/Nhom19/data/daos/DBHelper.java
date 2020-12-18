package org.tkxdpm20201.Nhom19.data.daos;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection connection;

    static {
        config.setAutoCommit(false);
        config.setJdbcUrl( "jdbc:postgresql://35.192.204.170:5432/tkxdpm" );
        config.setUsername( "postgres" );
        config.setPassword( "letuan123" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    public static Connection getConnection() throws SQLException {
            return connection;
    }

    public static void initConnection() throws SQLException {
        connection = ds.getConnection();
    }
    public static ResultSet executeQuery(String query) throws SQLException {
        try {
            if (connection == null) {
                initConnection();
            }
        return connection.createStatement().executeQuery(query);
        }
        catch (SQLException ex) {
            //create new connection in pool
            initConnection();
           ex.printStackTrace();
        }
        return null;
    }
    public static void rollBack() throws SQLException {
        connection.rollback();
    }
    public static void commit() throws SQLException {
        connection.commit();
    }
}
