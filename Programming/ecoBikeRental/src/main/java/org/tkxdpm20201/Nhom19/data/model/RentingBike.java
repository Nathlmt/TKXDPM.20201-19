package org.tkxdpm20201.Nhom19.data.model;

import org.tkxdpm20201.Nhom19.data.entities.Bike;

import java.util.Date;

public class RentingBike {

    private Bike bike;
    private Date startDate;

    public RentingBike(Bike bike, Date startDate) {
        this.bike = bike;
        this.startDate = startDate;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }





}
