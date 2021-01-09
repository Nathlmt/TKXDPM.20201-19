package org.tkxdpm20201.Nhom19.data.entities.bike;

import org.tkxdpm20201.Nhom19.data.entities.BaseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Bike extends BaseEntity {
    private String name;
    private String licensePlate;
    private BigDecimal price;
    private String type;
    private String status;
    private Timestamp lastUpdate;
    private Integer presentStation;

    public Bike(Integer id,String name, String licensePlate, BigDecimal price, String type, String status, Timestamp last_update, Integer presentStation) {
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
    public Bike() {

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

    public BigDecimal getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPresentStation() {
        return presentStation;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}