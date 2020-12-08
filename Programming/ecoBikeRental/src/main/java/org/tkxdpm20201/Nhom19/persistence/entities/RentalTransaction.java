package org.tkxdpm20201.Nhom19.persistence.entities;

public class RentalTransaction extends BaseEntity{

    private int rentalId;
    private int transactionId;

    public RentalTransaction(int rentalId) {
        this.rentalId = rentalId;
    }

    public RentalTransaction(Integer id, int rentalId) {
        super(id);
        this.rentalId = rentalId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }
}
