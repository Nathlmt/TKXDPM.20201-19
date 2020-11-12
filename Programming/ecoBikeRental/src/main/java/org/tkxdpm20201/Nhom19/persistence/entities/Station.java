package org.tkxdpm20201.Nhom19.persistence.entities;

import java.util.Date;

public class Station extends BaseEntity{

    private String stationName;
    private String address;
    private String acreage;
    private Integer availableBike;
    private Integer availableRack;
    private String status;
    private Date lastUpdate;

    public void addBike(Bike bike){

    }

    public void updateStationInfo(Station stationInfo){

    }

    public Station getStationInfo(){
        return this;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public Integer getAvailableBike() {
        return availableBike;
    }

    public void setAvailableBike(Integer availableBike) {
        this.availableBike = availableBike;
    }

    public Integer getAvailableRack() {
        return availableRack;
    }

    public void setAvailableRack(Integer availableRack) {
        this.availableRack = availableRack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
