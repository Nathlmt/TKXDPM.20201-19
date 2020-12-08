package org.tkxdpm20201.Nhom19.data.entities;

import org.tkxdpm20201.Nhom19.data.model.TransactionModel;

public class Transaction extends BaseEntity {

    private String amount;
    private String createdAt;
    private String transactionContent;
    private String command;
    private Integer cardId;

    public Transaction(String amount, String createdAt, String transactionContent, String command, Integer cardId) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.transactionContent = transactionContent;
        this.command = command;
        this.cardId = cardId;
    }

    public Transaction(TransactionModel transactionModel, Integer cardId){
        this.amount = transactionModel.getAmount();
        this.createdAt = transactionModel.getCreatedAt();
        this.transactionContent = transactionModel.getTransactionContent();
        this.command = transactionModel.getCommand();
        this.cardId = cardId;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
