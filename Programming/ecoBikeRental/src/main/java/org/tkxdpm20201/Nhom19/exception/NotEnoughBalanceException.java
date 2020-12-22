package org.tkxdpm20201.Nhom19.exception;


import org.tkxdpm20201.Nhom19.exception.PaymentException;

public class NotEnoughBalanceException extends PaymentException {

	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
