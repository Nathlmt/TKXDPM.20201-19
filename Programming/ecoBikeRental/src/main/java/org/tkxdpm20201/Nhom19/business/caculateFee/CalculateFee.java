package org.tkxdpm20201.Nhom19.business.caculateFee;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface CalculateFee {
    BigDecimal run(Timestamp startDate, Timestamp endDate, Bike bike);
    double run(Bike bike, long epoch);
}
