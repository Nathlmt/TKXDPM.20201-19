package org.tkxdpm20201.Nhom19.exception;


public class InvalidTransactionAmountException extends PaymentException {
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
