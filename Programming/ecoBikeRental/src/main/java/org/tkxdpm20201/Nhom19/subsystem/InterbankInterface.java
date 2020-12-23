package org.tkxdpm20201.Nhom19.subsystem;

import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author LyBaTuan, LeMinhTuan
 */
public interface InterbankInterface {
    /**
     * method use for refund
     * @param card: info card user enter
     * @param amount: amount refund
     * @param content: content of transaction
     * @return TransactionResponse
     * @throws IOException
     */
    TransactionResponse refund(Card card, BigDecimal amount, String content) throws IOException;

    /**
     * method use for pay
     * @param card
     * @param amount
     * @param content
     * @return TransactionResponse
     * @throws IOException
     */
    TransactionResponse pay(Card card, BigDecimal amount, String content) throws IOException;
}
