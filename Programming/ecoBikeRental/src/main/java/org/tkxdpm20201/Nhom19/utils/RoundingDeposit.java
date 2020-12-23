package org.tkxdpm20201.Nhom19.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingDeposit {

    public static String up(BigDecimal price){
        return price.multiply(new BigDecimal(Constants.DEPOSIT_PERCENT)).setScale(0, RoundingMode.HALF_UP).toString();
    }
}
