package org.tkxdpm20201.Nhom19.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalTransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalTransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.RentalTransaction;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalTransactionDaoTest {
    private RentalTransactionDao rentalTransactionDao;
    @BeforeEach
    void setup() throws SQLException {
        DBHelper.initConnection();
        this.rentalTransactionDao = new RentalTransactionDaoImp();
    }
    @Test
    void createRentalTransactionTest() throws SQLException {
            RentalTransaction rentalTransaction = new RentalTransaction(55,1);
            RentalTransaction testRentalTransaction = this.rentalTransactionDao.create(rentalTransaction);
            DBHelper.commit();
            assertEquals(testRentalTransaction.getRentalId(),55);
    }
}
