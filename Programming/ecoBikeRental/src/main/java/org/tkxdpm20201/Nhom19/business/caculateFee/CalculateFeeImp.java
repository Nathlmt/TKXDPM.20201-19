package org.tkxdpm20201.Nhom19.business.caculateFee;

import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.entities.bike.ElectricBike;
import org.tkxdpm20201.Nhom19.utils.Evaluation;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CalculateFeeImp implements CalculateFee{
    @Override
    public BigDecimal run(Timestamp startDate, Timestamp endDate, Bike bike) {
        long epoch = endDate.getTime() - startDate.getTime();
        double rentFee = 0;
        System.out.println("time second rented: " + epoch/ 1000L);
        long minutes = epoch/(1000*60);
        rentFee = 10;
        if(minutes > Evaluation.INITIALLY){
            long count = minutes/Evaluation.STEP + 1;
            rentFee += count*Evaluation.PLUS_STEP;
        }
        if(bike instanceof ElectricBike)
            rentFee = rentFee*Evaluation.SPECIAL_BIKE;

        return new BigDecimal(rentFee);
    }
}
