package org.tkxdpm20201.Nhom19.data.entities;

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

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRentStationId() {
        return rentStationId;
    }

    public void setRentStationId(int rentStationId) {
        this.rentStationId = rentStationId;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(int returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }
}
