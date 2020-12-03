package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.persistence.entities.Bike;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;
import org.tkxdpm20201.Nhom19.persistence.model.RentingBike;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionRequest;

public class RentBikeController {

    private static RentingBike rentingBike;

    public static RentingBike getRentingBike() {
        if(rentingBike == null){
            return null;
        }
        else
            return rentingBike;
    }


    /**
     *
     * @param transactionRequest
     * @param bike
     * @param station
     */
    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station){

    }


}
