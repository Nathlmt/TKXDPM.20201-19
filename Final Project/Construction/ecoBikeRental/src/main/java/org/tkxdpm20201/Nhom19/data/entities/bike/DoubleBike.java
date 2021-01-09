package org.tkxdpm20201.Nhom19.data.entities.bike;

import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFeeSpecialBike;

import java.math.BigDecimal;
import java.util.Date;

public class DoubleBike extends Bike{

    public DoubleBike(Integer id, String name, String licensePlate, BigDecimal price, String type, String status, Date last_update, Integer presentStation) {
        super(id, name, licensePlate, price, type, status, last_update, presentStation);
        calculateFee = new CalculateFeeSpecialBike();
    }

    public DoubleBike(Integer id, String type) {
        super(id, type);
    }

    public DoubleBike() {
    }
}
