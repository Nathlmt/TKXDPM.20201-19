package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Class provides method to get data from database;
 * @author LeMinhTuan
 */
public class BikeDaoImp extends BaseDaoImp<Bike> implements BikeDao {
    //*

    public BikeDaoImp(){
        super(Bike.class);
    }

    /**
     * Method use for get bike when have id
     * @param bikeCode:
     * @return bike:
     * @throws SQLException
     */
    @Override
    public Bike getById(int bikeCode) throws SQLException {
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

    /**
     *  method use for get bikes in a station
     * @param stationId:
     * @return
     * @throws SQLException
     */
    @Override
    public List<Bike> getAllBikeInStation(int stationId) throws SQLException {
        List<Bike> bikeList = new ArrayList<>();
        String sqlQuery = "SELECT * from BIKES WHERE present_station = ?";
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

    /**
     *
     * @param id
     * @param idStation
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateCurrentStation(int id, int idStation) throws SQLException {
        String status = Constants.AVAILABLE;
        String sqlUpdate = "UPDATE BIKES" +
                            " SET present_station = ?," +
                            " status = ?," +
                            " latest_update = ?" +
                            " WHERE id = ?";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        ps.setInt(1, idStation);
        ps.setString(2, status);
        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        ps.setInt(4, id);
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
