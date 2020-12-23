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

    public String getAddress() {
        return address;
    }

    public String getAcreage() {
        return acreage;
    }

    public Integer getAvailableBike() {
        return availableBike;
    }

    public Integer getAvailableRack() {
        return availableRack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStationName() {
        return stationName;
    }

}
