package org.tkxdpm20201.Nhom19.data.entities.bike;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ElectricBike extends Bike {

    private Integer battery;

    public Integer getBattery() {
        return battery;
    }


    public ElectricBike(Integer id, String name, String licensePlate, BigDecimal price, String type, String status, Timestamp lastest_update, Integer presentStation) {
        super(id, name, licensePlate, price, type, status, lastest_update, presentStation);
    }

    public ElectricBike(Integer id, String type) {
        super(id, type);
    }


}
