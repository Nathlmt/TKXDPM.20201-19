package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Transaction;

import java.sql.SQLException;

public interface TransactionDao extends BaseDao<Transaction> {
    public Transaction create(Transaction transaction) throws SQLException;

}
