package org.tkxdpm20201.Nhom19.data.entities.bike;

import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFee;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFeeImp;
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
    private Date lastUpdate;
    private Integer presentStation;

    protected CalculateFee calculateFee;

    public Bike(Integer id,String name, String licensePlate, BigDecimal price, String type, String status, Date last_update, Integer presentStation) {
        super(id);
        this.name = name;
        this.licensePlate = licensePlate;
        this.price = price;
        this.type = type;
        this.status = status;
        this.lastUpdate = last_update;
        this.presentStation = presentStation;
        calculateFee = new CalculateFeeImp();
    }
    public Bike(Integer id, String type){
        this.setId(id);
        this.type = type;
    }
    public Bike() {

    }

    public BigDecimal calculateFee(Timestamp startDate, Timestamp endDate){
        return calculateFee.run(startDate, endDate, this);
    }

    public double calculateFee(long epoch){
        return calculateFee.run(this, epoch);
    }

    public String getName() {
        return name;
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
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public Integer getPresentStation() {
        return presentStation;
    }

}