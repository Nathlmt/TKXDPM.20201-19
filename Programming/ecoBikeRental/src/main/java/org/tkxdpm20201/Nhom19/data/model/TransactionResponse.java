package org.tkxdpm20201.Nhom19.data.model;


public class TransactionResponse {

    private String errorCode;
    private TransactionModel transaction;

    public TransactionModel getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionModel transaction) {
        this.transaction = transaction;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
