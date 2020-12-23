package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class provide method insert/update rental table
 * @author LeMinhTuan, LyBaTuan
 */
public class RentalDaoImp extends BaseDaoImp<Rental> implements RentalDao {

    public RentalDaoImp() {
        super(Rental.class);
    }

    /**
     * Method use for create transaction
     * @param rental
     * @return rental: information of renting information
     * @throws SQLException:  true if the first result is a ResultSet object; false if the first result is an update count or there is no result
     */
    @Override
    public Rental create(Rental rental) throws SQLException {

        String sqlInsert = "INSERT INTO rental" +
                "(bike_id, customer_id, rent_station_id, status, time_start)" +
                "VALUES(?,?,?,?,?)"
                + "RETURNING id";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlInsert);
        prepareStatementCreate(ps, rental);
         ResultSet rs = ps.executeQuery();
         
         if (rs.next()) {
             Integer id = rs.getInt("id");
             rental.setId(id);
         }
        return rental;

    }

    @Override
    public Rental getByTransactionId(int transactionId){

        return null;
    }

    @Override
    public Rental getLatestRental() throws SQLException {
        String sqlQuery = "SELECT * FROM rental where customer_id=? ORDER BY id DESC LIMIT 1";
        PreparedStatement preparedStatement =  DBHelper.getConnection().prepareStatement(sqlQuery);
        preparedStatement.setInt(1, 1); // default customer id
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Rental rental = new Rental(rs.getInt("id"),
                    rs.getInt("bike_id"),
                    rs.getInt("rent_station_id"),
                    rs.getInt("return_station_id"),
                    rs.getTimestamp("time_start"),
                    rs.getString("status"),
                    rs.getTimestamp("time_end")
            );
            return rental;
        }
        return null;
    }

    /** Update information in rental table
     * Method use for update rental bike
     * @param rental: rental after update
     * @return true if update success
     * @throws SQLException:
     */
    @Override
    public boolean update(Rental rental) throws SQLException {

        String sqlUpdate = "UPDATE RENTAL " +
                            "SET bike_id = ?," +
                            "rent_station_id = ?," +
                            "return_station_id = ?," +
                            "status = ?," +
                            "time_start = ?," +
                            "time_end = ?" +
                            "WHERE id = ?";

        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        prepareStatementUpdate(ps, rental);
        return !ps.execute();
    }
    private void prepareStatementCreate(PreparedStatement preparedStatement, Rental rental) throws SQLException {
        preparedStatement.setInt(1, rental.getBikeId());
        preparedStatement.setInt(2, rental.getCustomerId());
        preparedStatement.setInt(3, rental.getRentStationId());
        preparedStatement.setString(4, rental.getStatus());
        preparedStatement.setTimestamp(5, rental.getTimeStart());
    }

    private void prepareStatementUpdate(PreparedStatement preparedStatement, Rental rental) throws SQLException {
        preparedStatement.setInt(1, rental.getBikeId());
        preparedStatement.setInt(2, rental.getRentStationId());
        preparedStatement.setInt(3, rental.getReturnStationId());
        preparedStatement.setString(4, rental.getStatus());
        preparedStatement.setTimestamp(5, rental.getTimeStart());
        preparedStatement.setTimestamp(6, rental.getTimeEnd());
        preparedStatement.setInt(7, rental.getId());
    }



}
