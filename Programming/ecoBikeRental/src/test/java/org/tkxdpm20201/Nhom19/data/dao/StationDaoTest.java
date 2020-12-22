package org.tkxdpm20201.Nhom19.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;

import java.sql.SQLException;

public class StationDaoTest {
    private StationDao stationDao = new StationDaoImp();
    @BeforeEach
    public void setUp() throws SQLException {
        DBHelper.initConnection();
    }
    @Test
    public void test() throws SQLException {
        stationDao.updateStationWhenRentBike(1);
        DBHelper.commit();
    }
}
