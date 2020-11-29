package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.Connect;
import org.tkxdpm20201.Nhom19.persistence.daos.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImp<T> implements BaseDao<T> {

    final Class<T> typeParamClass;

    /**
     *
     */
    private Connection connection;

    public BaseDaoImp(Class<T> typeParamClass){
        this.typeParamClass = typeParamClass;
    }

    public void connect() throws SQLException {
        this.connection = Connect.getConnection();
    }

    public ResultSet execQuery(String query) throws SQLException {
        connect();
        return this.connection.createStatement().executeQuery(query);
    }

    @Override
    public List<T> getAll() throws SQLException {
        String query = "SELECT * FROM " + typeParamClass;
        execQuery(query);
        System.out.println("ddd");
        return null;
    }

    @Override
    public T getById(Integer id) {
        return null;
    }

    @Override
    public T deleteById(Integer id) {
        return null;
    }


}
