package org.tkxdpm20201.Nhom19.data.model;

import org.tkxdpm20201.Nhom19.utils.Constants;

public class Caching {

    private RentingBike rentingBike;
    private boolean status;

    private static Caching instance;

    private Caching(){
        this.status = Constants.NOT_RENT_STATUS;
    }

    public static Caching getInstance(){
        if(instance == null)
            instance = new Caching();
        return instance;
    }

    public RentingBike getRentingBike(){
        return rentingBike;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setRentingBike(RentingBike rentingBike, boolean status){
        this.rentingBike = rentingBike;
        this.status = status;
    }

    public void resetCache(){
        this.rentingBike = null;
        this.status = Constants.NOT_RENT_STATUS;
    }
}
