package org.tkxdpm20201.Nhom19.exception;

public class InvalidCardException extends PaymentException {
    public InvalidCardException() {
        super("ERROR: Invalid Card!");
    }

}
