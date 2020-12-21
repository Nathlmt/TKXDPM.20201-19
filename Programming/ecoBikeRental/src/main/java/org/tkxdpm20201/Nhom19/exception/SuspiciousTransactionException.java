package org.tkxdpm20201.Nhom19.exception;


public class SuspiciousTransactionException extends PaymentException {
	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
	}
}
