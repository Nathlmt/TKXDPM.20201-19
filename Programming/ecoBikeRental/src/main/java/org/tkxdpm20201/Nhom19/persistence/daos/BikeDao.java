package org.tkxdpm20201.Nhom19.persistence.daos;

import org.tkxdpm20201.Nhom19.persistence.entities.Bike;

import java.sql.SQLException;
import java.util.List;

public interface BikeDao extends BaseDao<Bike> {

    List<Bike> getAllBikeInStation();

    int updateCurrentStation(int id, int idStation) throws SQLException;

}
