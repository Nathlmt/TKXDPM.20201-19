package org.tkxdpm20201.Nhom19.data.entities;

public class RentalTransaction extends BaseEntity{

    private int rentalId;
    private int transactionId;

    public RentalTransaction(int rentalId, int transactionId) {
        this.rentalId = rentalId;
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }
    public int getRentalId() {
        return rentalId;
    }

}
