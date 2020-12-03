package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.Bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikeDaoImp extends BaseDaoImp<Bike> implements BikeDao {

    public BikeDaoImp(){
        super(Bike.class);
    }
    public List<Bike> getAll() throws SQLException {
        String sqlSelect = "SELECT * from BIKES";
        List<Bike> BikeList = new ArrayList<>();
        ResultSet rs = DBHelper.executeQuery(sqlSelect);
        while(true){
            assert rs != null;
            if (!rs.next()) break;
            Bike bike = new Bike(rs.getInt("id"),
                    rs.getString("bike_name"),
                    rs.getString("licence_plate"),
                    rs.getBigDecimal("price"),
                    rs.getInt("batery"),
                    rs.getString("bike_type"),
                    rs.getString("status"),
                    rs.getDate("latest_update"),
                    rs.getInt("present_station")
            );
            BikeList.add(bike);
        }
        return BikeList;
    }

    @Override
    public Bike getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Bike> getAllBikeInStation() {
        return null;
    }
}