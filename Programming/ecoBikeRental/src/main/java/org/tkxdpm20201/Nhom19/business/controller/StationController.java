package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Station;

import java.sql.SQLException;
import java.util.List;

public class StationController<stationDao> {
    private final StationDaoImp stationDao;
    public StationController() {
        stationDao = new StationDaoImp();
    }
    public Bike getBikeInfo(int bikeId) {
        return null;
    }

    public List<Station> getStationList() throws SQLException {
        return this.stationDao.getAll();
    }

    public List<Station> searchStation(String name) {
        return null;
    }

    public Station getStationInfo(int stationId) {

        return null;
    }
}
