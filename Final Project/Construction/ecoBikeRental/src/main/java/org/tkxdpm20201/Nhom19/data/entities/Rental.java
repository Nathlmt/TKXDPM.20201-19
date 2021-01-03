package org.tkxdpm20201.Nhom19.data.entities;

import java.sql.Time;
import java.sql.Timestamp;

public class Rental extends BaseEntity {

    private int bikeId;
    private int customerId;
    private int rentStationId;
    private int returnStationId;
    private String status;
    private Timestamp timeStart;
    private Timestamp timeEnd;

    public Rental(int bikeId, int customerId, int rentStationId, String status, Timestamp timeStart) {
        this.bikeId = bikeId;
        this.customerId = customerId;
        this.rentStationId = rentStationId;
        this.status = status;
        this.timeStart = timeStart;
    }
    public Rental(int id, int bikeId, int rentStationId, int returnStationId, Timestamp timeStart, String status, Timestamp timeEnd) {
        this.bikeId = bikeId;
        this.setId(id);
        this.rentStationId = rentStationId;
        this.returnStationId = returnStationId;
        this.status = status;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public void updateRentalWhenReturnBike(int rentStationId, String status, Timestamp timeEnd){
        this.rentStationId = rentStationId;
        this.status = status;
        this.timeEnd = timeEnd;
    }


    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRentStationId() {
        return rentStationId;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

}
