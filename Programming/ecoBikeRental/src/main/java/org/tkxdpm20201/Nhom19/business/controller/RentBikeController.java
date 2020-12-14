package org.tkxdpm20201.Nhom19.business.controller;

import javafx.scene.control.TextField;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;

import java.sql.SQLException;

public class RentBikeController {

    private BikeDaoImp bikeDao = new BikeDaoImp();


    /**
     *
     * @param transactionRequest
     * @param bike
     * @param station
     */
    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station){

    }

    public Bike getBikeInfo(int bikeCode) throws SQLException {
        return bikeDao.getBikeById(bikeCode);
    }


}
