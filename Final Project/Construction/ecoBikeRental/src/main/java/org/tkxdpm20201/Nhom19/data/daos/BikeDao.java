package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.sql.SQLException;
import java.util.List;

public interface BikeDao extends BaseDao<Bike> {

    List<Bike> getAllBikeInStation(int stationId) throws SQLException;
    boolean updateCurrentStation(int id, int idStation) throws SQLException;
    boolean updateStatusBike(int idBike, String status) throws SQLException;
}
