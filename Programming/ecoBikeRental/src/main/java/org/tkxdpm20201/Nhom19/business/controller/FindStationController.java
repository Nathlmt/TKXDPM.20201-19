package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;

import java.sql.SQLException;
import java.util.List;


public class FindStationController extends BaseController{
    private final StationDao stationDao = new StationDaoImp();

    public List<Station> getStationList() throws SQLException {
        List<Station> stationList = stationDao.getAll();
        return stationList;
    }
}
