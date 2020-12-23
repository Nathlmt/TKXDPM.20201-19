package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Rental;

import java.sql.SQLException;

public interface RentalDao extends BaseDao<Rental>{

    Rental getByTransactionId(int transactionId);
    Rental getLatestRental() throws SQLException;
}
