package org.tkxdpm20201.Nhom19.persistence.daos;

import org.tkxdpm20201.Nhom19.persistence.entities.Bike;

import java.util.List;

public interface BikeDao extends BaseDao<Bike> {

    List<Bike> getAllBikeInStation();
}
