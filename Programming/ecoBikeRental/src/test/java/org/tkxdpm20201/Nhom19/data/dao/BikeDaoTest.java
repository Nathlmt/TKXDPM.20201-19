package org.tkxdpm20201.Nhom19.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class BikeDaoTest {
    private BikeDao bikeDao;
    @BeforeEach
    void setUp() throws SQLException {
        DBHelper.initConnection();
         this.bikeDao = new BikeDaoImp();
    }
    @Test
    void updateBikeStatus() throws SQLException {
        boolean a = bikeDao.updateStatusBike(2,"renting");
        DBHelper.commit();
        assertFalse(a);
    }
}
