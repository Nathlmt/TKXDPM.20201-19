package org.tkxdpm20201.Nhom19.persistence;

import org.tkxdpm20201.Nhom19.persistence.daos.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(Dao.HOST, Dao.USER, Dao.PASS);
            if(connection != null){
                System.out.println("=============== Connected PostgreSQL ===========");
                return connection;
            }
            else{
                System.out.println("Connect Fail!");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
