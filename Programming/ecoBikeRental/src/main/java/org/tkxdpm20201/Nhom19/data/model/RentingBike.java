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

    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private int cardId;

    private Rental rental;


    public RentingBike(Bike bike, Card card, Rental rental) {
        this.cardId = card.getId();
        this.cardCode = card.getCardCode();
        this.owner = card.getOwner();
        this.cvvCode = card.getCvvCode();
        this.dateExpired = card.getDateExpired();
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

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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
