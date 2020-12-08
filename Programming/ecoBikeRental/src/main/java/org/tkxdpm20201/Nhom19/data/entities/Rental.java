package org.tkxdpm20201.Nhom19.data.entities;

public class Rental extends BaseEntity {

    private int bikeId;
    private int customerId;
    private int rentStationId;
    private int returnStationId;
    private String status;
    private String timeStart;
    private String timeEnd;



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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
