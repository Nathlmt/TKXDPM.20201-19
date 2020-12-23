package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.station.Station;

import java.sql.SQLException;

public interface StationDao extends BaseDao<Station> {

     boolean updateStationWhenRentBike(int stationId) throws SQLException;
}
