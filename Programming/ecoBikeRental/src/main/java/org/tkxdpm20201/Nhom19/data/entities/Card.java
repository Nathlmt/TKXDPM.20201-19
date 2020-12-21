package org.tkxdpm20201.Nhom19.data.entities;

import java.math.BigDecimal;

public class Card extends BaseEntity{
    private BigDecimal balance;
    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;

    public Card(){

    }

    public Card(Integer id, BigDecimal balance, String cardCode, String owner, String cvvCode, String dateExpired) {
        super(id);
        this.balance = balance;
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    public Card(BigDecimal balance, String cardCode, String owner, String cvvCode, String dateExpired) {
        this.balance = balance;
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    public Card(String cardCode, String owner, String cvvCode, String dateExpired) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }
}
