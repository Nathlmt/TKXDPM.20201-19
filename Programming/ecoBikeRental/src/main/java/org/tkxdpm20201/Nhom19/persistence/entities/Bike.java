package org.tkxdpm20201.Nhom19.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Bike  extends BaseEntity{

    private String name;
    private String licensePlate;
    private BigDecimal price;
    private Integer battery;
    private String type;
    private String status;
    private Date lastUpdate;
    private Integer presentStation;     // TODO: object Station

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getPresentStation() {
        return presentStation;
    }

    public void setPresentStation(Integer presentStation) {
        this.presentStation = presentStation;
    }
}
