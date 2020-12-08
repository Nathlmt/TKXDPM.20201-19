package org.tkxdpm20201.Nhom19.data.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Bike extends BaseEntity{
    private String name;
    private String licensePlate;
    private BigDecimal price;
    private String type;
    private String status;
    private Date lastUpdate;
    private Integer presentStation;

    public Bike(Integer id,String name, String licensePlate, BigDecimal price, String type, String status, Date last_update, Integer presentStation) {
        super(id);
        this.name = name;
        this.licensePlate = licensePlate;
        this.price = price;
        this.type = type;
        this.status = status;
        this.lastUpdate = last_update;
        this.presentStation = presentStation;
    }

    public Bike(Integer id, String type){
        this.setId(id);
        this.type = type;
    }

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
