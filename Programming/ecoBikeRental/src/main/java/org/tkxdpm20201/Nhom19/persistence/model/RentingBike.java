package org.tkxdpm20201.Nhom19.persistence.model;

import org.tkxdpm20201.Nhom19.persistence.entities.Bike;
import org.tkxdpm20201.Nhom19.persistence.entities.Card;
import org.tkxdpm20201.Nhom19.persistence.entities.Rental;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentingBike {

    private Bike bike;
    private BigDecimal deposit;
    private final LocalDateTime startDate;

    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;

    private Rental rental;

    public RentingBike(Bike bike, Card card) {
        this.cardCode = card.getCardCode();
        this.owner = card.getOwner();
        this.cvvCode = card.getCvvCode();
        this.dateExpired = card.getDateExpired();
        this.bike = bike;
        this.deposit = new BigDecimal(bike.getPrice().intValue() * Constants.DEPOSIT_PERCENT);
        this.startDate = java.time.LocalDateTime.now();
    }



    public BigDecimal getDeposit() {
        return deposit;
    }

    public Bike getBike() {
        if(this.bike == null)
            return null;
        return bike;
    }


    public LocalDateTime getStartDate() {
        if(this.startDate == null)
            return null;
        return startDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }
}
