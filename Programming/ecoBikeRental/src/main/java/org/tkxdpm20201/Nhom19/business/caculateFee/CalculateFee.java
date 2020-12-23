package org.tkxdpm20201.Nhom19.business.caculateFee;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
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
     * calculate rent fee use ms
     * @param bike
     * @param epoch: time in ms
     * @return fee
     */
    double run(Bike bike, long epoch);
}
