package org.tkxdpm20201.Nhom19.business.calculateFee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runners.Parameterized.Parameters;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFee;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFeeImp;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaculateFeeTest {
    private final CalculateFee calculator = new CalculateFeeImp();

    @ParameterizedTest
    @CsvSource(
            {
                    "1, Xe đạp điện, 1000, 15000",
                    "1, Xe đạp điện, 4200000, 37500",
                    "1, Xe đạp đôi, 1000, 15000",
                    "1, Xe đạp đôi, 4200000, 37500",
                    "1, Xe đạp, 1000, 10000",
                    "1, Xe đạp, 4200000, 25000",

            }
    )
    void testCalculateFee(int bikeId, String bikeType, long time, Double price) {
        Bike bike = new Bike(1, bikeType);
        assertEquals(price,calculator.run(bike,time));
    }
}
