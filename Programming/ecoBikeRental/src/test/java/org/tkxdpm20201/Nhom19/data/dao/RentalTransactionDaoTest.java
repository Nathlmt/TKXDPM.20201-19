package org.tkxdpm20201.Nhom19.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalTransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalTransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.RentalTransaction;

import java.sql.SQLException;

public class RentalTransactionDaoTest {
    private RentalTransactionDao rentalTransactionDao;
    @BeforeEach
    void setup() throws SQLException {
        DBHelper.initConnection();
        this.rentalTransactionDao = new RentalTransactionDaoImp();
    }
    @Test
    void createRentalTransactionTest() throws SQLException {
            RentalTransaction rentalTransaction = new RentalTransaction(12,1);
            this.rentalTransactionDao.create(rentalTransaction);
            DBHelper.commit();
    }
}
