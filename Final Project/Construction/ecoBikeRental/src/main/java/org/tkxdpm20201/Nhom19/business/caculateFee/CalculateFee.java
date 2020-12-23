package org.tkxdpm20201.Nhom19.business.caculateFee;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * when rent a bike fee in first 30 minutes is 10.000 vnd for normal bike, 15000vnd for electric bike and double bike
 * after 30 minutes fee is 3000vnd per 15 minute (normal bikes) and x1.5 for other bikes
 * Class use for calculate fee
 * @author LyBaTuan
 */
public interface CalculateFee {
    /**
     * calculate rent fee use timestamp
     * @param startDate: startTime rent bike (query db)
     * @param endDate: endTime rent bike
     * @param bike: bike info
     * @return fee
     */
    BigDecimal run(Timestamp startDate, Timestamp endDate, Bike bike);

    /**
     * calculate rent fee in ms
     * @param bike
     * @param epoch: time in ms
     * @return fee
     */
    double run(Bike bike, long epoch);
}
