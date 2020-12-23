package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalTransactionDao;
import org.tkxdpm20201.Nhom19.data.entities.RentalTransaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class use for rental transaction table
 */
public class RentalTransactionDaoImp extends BaseDaoImp<RentalTransaction> implements RentalTransactionDao {

    public RentalTransactionDaoImp() {
        super(RentalTransaction.class);
    }

    @Override
    public RentalTransaction create(RentalTransaction rentalTransaction) throws SQLException {
        String sqlInsert = "INSERT INTO rental_transaction" +
                "(rental_id, transaction_id)" +
                "VALUES(?,?)"
                + "RETURNING id";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlInsert);
        ps.setInt(1,rentalTransaction.getRentalId());
        ps.setInt(2, rentalTransaction.getTransactionId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Integer id = rs.getInt("id");
            rentalTransaction.setId(id);
        }
        return rentalTransaction;
    }
}
