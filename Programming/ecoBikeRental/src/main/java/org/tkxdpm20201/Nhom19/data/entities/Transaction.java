package org.tkxdpm20201.Nhom19.data.entities;

import org.tkxdpm20201.Nhom19.data.model.TransactionModel;

public class Transaction extends BaseEntity {
    private int id;
    private String amount;
    private String createdAt;
    private String transactionContent;
    private String command;
    private String cardCode;
    private String apiId;

    public Transaction(String amount, String createdAt, String transactionContent, String command, String cardCode) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.transactionContent = transactionContent;
        this.command = command;
        this.cardCode = cardCode;
    }

    public Transaction(TransactionModel transactionModel, String cardCode){
        this.amount = transactionModel.getAmount();
        this.createdAt = transactionModel.getCreatedAt();
        this.transactionContent = transactionModel.getTransactionContent();
        this.command = transactionModel.getCommand();
        this.cardCode = cardCode;
        this.apiId = transactionModel.getTransactionId();
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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    };

    public void setId(int id) {
        this.id = id;
    };
    public Integer getId() {
        return id;
    };

    public void setApiId(String apiId) {
        this.apiId = apiId;
    };

    public String getApiId() {
        return apiId;
    }
}
