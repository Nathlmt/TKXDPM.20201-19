package org.tkxdpm20201.Nhom19.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lytuan
 * round up the deposit
 */
public class RoundingDeposit {

    /**
     * round up the price
     * @param price
     * @return prices are rounded
     */
    public static String up(BigDecimal price){
        return price.multiply(new BigDecimal(Constants.DEPOSIT_PERCENT)).setScale(0, RoundingMode.HALF_UP).toString();
    }
}
