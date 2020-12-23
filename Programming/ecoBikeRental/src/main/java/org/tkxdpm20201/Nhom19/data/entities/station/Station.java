package org.tkxdpm20201.Nhom19.data.entities.station;

import org.tkxdpm20201.Nhom19.data.entities.BaseEntity;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.util.Date;

public class Station extends BaseEntity {

    private String stationName;
    private String address;
    private String acreage;
    private Integer availableBike;
    private Integer availableRack;
    private String status;
    private Date lastUpdate;

    public Station(Integer id, String stationName, String address, String acreage, Integer availableBike, Integer availableRack, String status, Date lastUpdate) {
        super(id);
        this.stationName = stationName;
        this.address = address;
        this.acreage = acreage;
        this.availableBike = availableBike;
        this.availableRack = availableRack;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }


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
