package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.entities.Rental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalDaoImp extends BaseDaoImp<Rental> implements RentalDao {

    public RentalDaoImp() {
        super(Rental.class);
    }

    @Override
    public Rental create(Rental rental) throws SQLException {

        String sqlInsert = "INSERT INTO rental" +
                "(bike_id, customer_id, rent_station_id, status, time_start)" +
                "VALUES(?,?,?,?,?)";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlInsert);
        prepareStatement(ps, rental);
        ResultSet resultSet =  ps.executeQuery();
        //TODO: get Id rental ra vào set lại vào rental

        return rental;
    }

    @Override
    public Rental getByTransactionId(int transactionId){

        return null;
    }

    @Override
    public boolean update(Rental rental) throws SQLException {

        String sqlUpdate = "UPDATE RENTALS " +
                            "SET bike_id = ?" +
                            "rent_station_id = ?" +
                            "return_station_id = ?" +
                            "status = ?" +
                            "time_start = ?" +
                            "time_end = ?" +
                            "WHERE id = ?";

        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        prepareStatementUpdate(ps, rental);
        return ps.execute();
    }
    private void prepareStatement(PreparedStatement preparedStatement, Rental rental) throws SQLException {
        preparedStatement.setInt(1, rental.getBikeId());
        preparedStatement.setInt(2, rental.getRentStationId());
        preparedStatement.setInt(3, rental.getReturnStationId());
        preparedStatement.setString(4, rental.getStatus());
        preparedStatement.setTimestamp(5, rental.getTimeStart());
    }
    private void prepareStatementUpdate(PreparedStatement preparedStatement, Rental rental) throws SQLException {
        preparedStatement.setInt(1, rental.getBikeId());
        preparedStatement.setInt(2, rental.getRentStationId());
        preparedStatement.setString(4, rental.getStatus());
        preparedStatement.setTimestamp(5, rental.getTimeStart());
        preparedStatement.setTimestamp(6, rental.getTimeEnd());
        preparedStatement.setInt(7, rental.getId());
    }



}
