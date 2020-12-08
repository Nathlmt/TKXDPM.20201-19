package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.entities.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDaoImp extends BaseDaoImp<Transaction> implements TransactionDao {

    public TransactionDaoImp() {
        super(Transaction.class);
    }

    public Transaction create(Transaction transaction) throws SQLException {

        String sqlInsert = "INSERT INTO transactions" +
                "(id, amount, transaction_content, card_id, created_at, command)" +
                " VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlInsert);
        ps.setInt(1, transaction.getId());
        ps.setString(2, transaction.getAmount());
        ps.setString(3, transaction.getTransactionContent());
        ps.setInt(4, transaction.getCardId());
        ps.setString(5, transaction.getCreatedAt());
        ps.setString(6, transaction.getCommand());
        boolean result = ps.execute();

        return result ? transaction : null;
    }
}
