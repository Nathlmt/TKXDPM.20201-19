package org.tkxdpm20201.Nhom19.data.entities;

import java.math.BigDecimal;
import java.util.Date;

public class ElectricBike extends Bike {

    public ElectricBike(Integer id, String name, String licensePlate, BigDecimal price, String type, String status, Date lastest_update, Integer presentStation) {
        super(id, name, licensePlate, price, type, status, lastest_update, presentStation);
    }

    public ElectricBike(Integer id, String type) {
        super(id, type);
    }


}
