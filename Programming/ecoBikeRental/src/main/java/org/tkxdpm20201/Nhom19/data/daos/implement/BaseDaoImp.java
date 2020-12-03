package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class BaseDaoImp<T> implements BaseDao<T> {

    final Class<T> typeParamClass;
    public BaseDaoImp(Class<T> typeParamClass){
        this.typeParamClass = typeParamClass;
    }
    @Override
    public List<T> getAll() throws SQLException {
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
