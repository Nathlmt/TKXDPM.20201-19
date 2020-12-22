package org.tkxdpm20201.Nhom19.data.model;

import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionRequest {

    private String transactionContent;
    private BigDecimal amount;
    private Date createdAt;

    private Card card;
    private String command;

    public TransactionRequest() {
    }

    public TransactionRequest(String transactionContent, BigDecimal amount, Date createdAt, Card card) {
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
        this.card = card;
    }

    public void setTransactionRequestToReturnBike(RentingBike rentingBike, BigDecimal amount, String command){
        this.transactionContent = Constants.RETURNED_BIKE;
        this.amount = amount;
//        this.cardCode = rentingBike.getCardCode();
//        this.owner = rentingBike.getOwner();
//        this.cvvCode = rentingBike.getCvvCode();
//        this.dateExpired = rentingBike.getDateExpired();
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Card getCard() {
        return card;
    }
}
