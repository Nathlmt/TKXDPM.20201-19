package org.tkxdpm20201.Nhom19.subsystem;

import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;

import java.io.IOException;
import java.math.BigDecimal;

public interface InterbankInterface {
    public TransactionResponse refund(Card card, BigDecimal amount, String content) throws IOException;
    public TransactionResponse pay(Card card, BigDecimal amount, String content) throws IOException;
}
