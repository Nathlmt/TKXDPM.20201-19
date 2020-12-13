package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Bike;

import java.sql.SQLException;
import java.util.List;

public interface BikeDao extends BaseDao<Bike> {

    List<Bike> getAllBikeInStation(int stationId);
    Bike getBikeById(int bikeCode) throws SQLException;
    boolean updateCurrentStation(int id, int idStation) throws SQLException;

}
