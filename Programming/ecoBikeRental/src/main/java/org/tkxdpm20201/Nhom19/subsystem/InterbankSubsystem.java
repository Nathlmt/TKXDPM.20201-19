package org.tkxdpm20201.Nhom19.subsystem;

import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.subsystem.interbank.InterbankController;
import java.io.IOException;
import java.math.BigDecimal;

public class InterbankSubsystem implements InterbankInterface  {
    private InterbankController controller;
    public InterbankSubsystem() {
        this.controller = new InterbankController();
    }
    @Override
    public TransactionResponse pay(Card card, BigDecimal amount,String content) throws IOException {
        return controller.pay(card, amount, content);
    }
    @Override
    public TransactionResponse refund(Card card, BigDecimal amount,String content) throws IOException {
        return controller.refund(card, amount, content);
    };
}
