package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.entities.Rental;

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
     * @return
     * @throws SQLException
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

    /**
     * Method use for update rental bike
     * @param rental
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
