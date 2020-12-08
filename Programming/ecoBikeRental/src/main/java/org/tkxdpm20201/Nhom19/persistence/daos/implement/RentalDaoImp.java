package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.DBHelper;
import org.tkxdpm20201.Nhom19.persistence.daos.RentalDao;
import org.tkxdpm20201.Nhom19.persistence.entities.Rental;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentalDaoImp extends BaseDaoImp<Rental> implements RentalDao {

    public RentalDaoImp() {
        super(Rental.class);
    }

    @Override
    public Rental create(Rental rental){

        return null;
    }

    @Override
    public Rental getByTransactionId(int transactionId){

        return null;
    }

    @Override
    public int update(Rental rental) throws SQLException {

        String sqlUpdate = "UPDATE RENTALS " +
                            "SET bike_id = ?" +
                            "rent_station_id = ?" +
                            "return_station_id = ?" +
                            "status = ?" +
                            "time_start = ?" +
                            "time_end = ?" +
                            "WHERE id = ?";

        PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, rental.getBikeId());
        preparedStatement.setInt(2, rental.getRentStationId());
        preparedStatement.setInt(3, rental.getReturnStationId());
        preparedStatement.setString(4, rental.getStatus());
        preparedStatement.setString(5, rental.getTimeStart());
        preparedStatement.setString(6, rental.getTimeEnd());
        preparedStatement.setInt(7, rental.getId());
        return preparedStatement.executeUpdate();
    }



}
