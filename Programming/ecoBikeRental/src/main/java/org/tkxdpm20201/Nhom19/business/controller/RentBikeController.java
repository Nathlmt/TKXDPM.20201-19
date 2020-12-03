package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.model.RentingBike;

public class RentBikeController {

    private static RentingBike rentingBike;

    public static RentingBike getRentingBike() {
        if(rentingBike == null){

            return null;
        }
        else
            return rentingBike;
    }
}
