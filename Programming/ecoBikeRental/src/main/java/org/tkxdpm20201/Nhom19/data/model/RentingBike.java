package org.tkxdpm20201.Nhom19.data.model;

import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RentingBike {

    private Bike bike;
    private BigDecimal deposit;
    private Timestamp startDate;
    private Card card;

    private Rental rental;


    public RentingBike(Bike bike, Card card, Rental rental) {
        this.card = card;
        this.bike = bike;
        this.deposit = new BigDecimal(bike.getPrice().intValue() * Constants.DEPOSIT_PERCENT);
        this.rental = rental;
        this.startDate = rental.getTimeStart();
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Rental getRental() {
        return rental;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public Bike getBike() {
        if(this.bike == null)
            return null;
        return bike;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Card getCard() {
        return card;
    }
}
