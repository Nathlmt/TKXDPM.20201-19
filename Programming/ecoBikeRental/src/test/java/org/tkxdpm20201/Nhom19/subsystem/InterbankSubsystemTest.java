package org.tkxdpm20201.Nhom19.subsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterbankSubsystemTest {
    private InterbankInterface interbankInterface;
    @BeforeEach
    void setUp() throws Exception {

        interbankInterface = new InterbankSubsystem();
    }
    @Test
    public void test() throws IOException {
        Card card = new Card("118609_group19_2020", "Group 19", "907", "1125");
        TransactionResponse transactionResponse = interbankInterface.refund(card, BigDecimal.valueOf(1000.00), "Hello");
        assertEquals("00", transactionResponse.getErrorCode());
    }
}
