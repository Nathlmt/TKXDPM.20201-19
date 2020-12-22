package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.RentalTransaction;

import java.sql.SQLException;

public interface RentalTransactionDao {
     RentalTransaction create(RentalTransaction rentalTransaction) throws SQLException;
}
