package org.tkxdpm20201.Nhom19.data.model;

import org.tkxdpm20201.Nhom19.utils.Constants;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionRequest {

    private String transactionContent;
    private BigDecimal amount;
    private Date createdAt;

    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private String command;

    public TransactionRequest() {
    }

    public TransactionRequest(String transactionContent, BigDecimal amount, Date createdAt, String cardCode, String owner, String cvvCode, String dateExpired) {
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }


    /**
     * set transaction data to execute returning Bike
     * @param rentingBike
     * @param deposit
     * @param rentingFee
     */
    public void setTransactionRequestToReturnBike(RentingBike rentingBike, BigDecimal amount, String command){
        this.transactionContent = Constants.RETURNED_BIKE;
        this.amount = amount;
        this.cardCode = rentingBike.getCardCode();
        this.owner = rentingBike.getOwner();
        this.cvvCode = rentingBike.getCvvCode();
        this.dateExpired = rentingBike.getDateExpired();
        this.command = command;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
