package org.tkxdpm20201.Nhom19.business.controller;

import javafx.scene.control.TextField;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RentBikeController extends BaseController {

    /**
     * Caching information when renting bike
     */
    private static RentingBike rentingBike;
    private BikeDao bikeDao = new BikeDaoImp();


    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station){

    }

    public Bike getBikeInfo(int bikeCode) throws SQLException {
        return bikeDao.getBikeById(bikeCode);
    }

    /**
     * Thuc thi sau khi nguoi dung bam thanh toan
     * @param bikeId: id cua xe
     * @param customerId: id cua khach hang
     * @param rentStationId: id cua tram thue xe
     */
    public void handleRentBike(int bikeId,  int customerId, int rentStationId) throws SQLException {
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        //Tao doi tuong rental de cap nhat len database
        Rental rental = new Rental(bikeId, customerId, rentStationId, "renting",startTime);
        RentalDao rentalDao = new RentalDaoImp();
        rentalDao.create(rental);
    }

    public boolean validateCartInfo() {
        return true;
    }


    public boolean validateName() {
        return false;
    }


}
