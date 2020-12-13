package org.tkxdpm20201.Nhom19.data.daos;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {

     List<T> getAll() throws SQLException;

     T getById(Integer id);

     T deleteById(Integer id);

     T create(T object) throws SQLException;

     boolean update(T object) throws SQLException;

}
