package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.entities.Transaction;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 */
public class TransactionDaoImp extends BaseDaoImp<Transaction> implements TransactionDao {

    public TransactionDaoImp() {
        super(Transaction.class);
    }

    /**
     *
     * @param transaction ;
     * @return transaction;
     * @throws SQLException:
     */
    public Transaction create(Transaction transaction) throws SQLException {

        String sqlInsert = "INSERT INTO transactions" +
                "(api_id, amount, transaction_content, card_code, created_at, command)" +
                " VALUES(?,?,?,?,?,?) " +
                "RETURNING id";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlInsert);
        ps.setString(1, transaction.getApiId());
        ps.setBigDecimal(2, new BigDecimal(transaction.getAmount()));
        ps.setString(3, transaction.getTransactionContent());
        ps.setString(4, transaction.getCardCode());
        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
        ps.setString(6, transaction.getCommand());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            transaction.setId(id);
        }
        return transaction;
    }
}
