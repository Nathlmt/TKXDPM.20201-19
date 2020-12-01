package org.tkxdpm20201.Nhom19.persistence.model;

import org.tkxdpm20201.Nhom19.persistence.entities.Bike;

import java.time.LocalDateTime;

public class RentingBike {

    private static Bike bike;
    private static LocalDateTime startDate;

    public RentingBike(Bike bike) {
        RentingBike.bike = bike;
        RentingBike.startDate = java.time.LocalDateTime.now();
    }



    public Bike getBike() {
        if(RentingBike.bike == null)
            return null;
        return bike;
    }


    public LocalDateTime getStartDate() {
        if(RentingBike.startDate == null)
            return null;
        return startDate;
    }






}
