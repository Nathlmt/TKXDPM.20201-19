package org.tkxdpm20201.Nhom19.business.caculateFee;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.utils.Evaluation;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CalculateFeeSpecialBike extends CalculateFeeImp implements CalculateFee{
    @Override
    public BigDecimal run(Timestamp startDate, Timestamp endDate, Bike bike) {
        long epoch = endDate.getTime() - startDate.getTime();
        double rentFee = run(bike, epoch);
        return new BigDecimal(rentFee);
    }

    @Override
    public double run(Bike bike, long epoch) {
        double rentFee = super.run(bike, epoch);
        return rentFee * Evaluation.SPECIAL_BIKE;

    }
}
