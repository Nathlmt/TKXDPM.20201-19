package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BikeDaoImp extends BaseDaoImp<Bike> implements BikeDao {

    public BikeDaoImp(){
        super(Bike.class);
    }
    @Override
    public Bike getBikeById(int bikeCode) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sqlQuery = "SELECT * FROM BIKES WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
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
    public List<Bike> getAllBikeInStation(int stationId) {

        return null;
    }

    @Override
    public boolean updateCurrentStation(int id, int idStation) throws SQLException {
        String sqlUpdate = "UPDATE BIKES" +
                            "SET present_station = ?" +
                            " last_update = ?" +
                            "WHERE id = ?";
        PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, idStation);
        preparedStatement.setString(2, DateUtil.format(new Date()));
        preparedStatement.setInt(3, id);
        return preparedStatement.execute();
    }
    @Override
    public boolean updateStatusBike(int idBike, String status) throws SQLException {
        String sqlUpdate = "UPDATE BIKES" +
                " SET status = ?," +
                " latest_update = ?" +
                " WHERE id = ?";
        PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(sqlUpdate);
        preparedStatement.setString(1, "renting");
        preparedStatement.setTimestamp(2,  new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(3, idBike);
        return preparedStatement.execute();
    }
}
