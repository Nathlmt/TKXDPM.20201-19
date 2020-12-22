package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LeTuan
 */
public class BikeDaoImp extends BaseDaoImp<Bike> implements BikeDao {

    public BikeDaoImp(){
        super(Bike.class);
    }
    @Override
    public Bike getBikeById(int bikeCode) throws SQLException {
        String sqlQuery = "SELECT * FROM BIKES WHERE id = ?";
        PreparedStatement preparedStatement =  DBHelper.getConnection().prepareStatement(sqlQuery);
        preparedStatement.setInt(1, bikeCode);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Bike bike = new Bike(rs.getInt("id"),
                    rs.getString("bike_name"),
                    rs.getString("license_plate"),
                    rs.getBigDecimal("price"),
                    rs.getString("bike_type"),
                    rs.getString("status"),
                    rs.getDate("latest_update"),
                    rs.getInt("present_station")
                    );
            return bike;
        }
        return null;
    }

    @Override
    public List<Bike> getAllBikeInStation(int stationId) throws SQLException {
        List<Bike> bikeList = new ArrayList<>();
        String sqlQuery = "SELECT * from BIKES WHERE present_station = ? AND status = 'available'";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlQuery);
        ps.setInt(1,stationId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Bike bike = new Bike(rs.getInt("id"),
                    rs.getString("bike_name"),
                    rs.getString("license_plate"),
                    rs.getBigDecimal("price"),
                    rs.getString("bike_type"),
                    rs.getString("status"),
                    rs.getDate("latest_update"),
                    rs.getInt("present_station")
            );
            bikeList.add(bike);
        }
        return bikeList;
    }

    @Override
    public boolean updateCurrentStation(int id, int idStation) throws SQLException {
        String sqlUpdate = "UPDATE BIKES" +
                            "SET present_station = ?," +
                            " status = available," +
                            " last_update = ?" +
                            "WHERE id = ?";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        ps.setInt(1, idStation);
        ps.setString(2, DateUtil.format(new Date()));
        ps.setInt(3, id);
        return ps.execute();
    }
    @Override
    public boolean updateStatusBike(int idBike, String status) throws SQLException {
        String sqlUpdate = "UPDATE BIKES" +
                " SET status = ?," +
                " latest_update = ?" +
                " WHERE id = ?";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        ps.setString(1, "renting");
        ps.setTimestamp(2,  new Timestamp(System.currentTimeMillis()));
        ps.setInt(3, idBike);
        return ps.execute();
    };
}
