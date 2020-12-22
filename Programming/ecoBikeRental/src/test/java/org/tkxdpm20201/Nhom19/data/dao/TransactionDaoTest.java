package org.tkxdpm20201.Nhom19.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.TransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.Transaction;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.subsystem.InterbankInterface;
import org.tkxdpm20201.Nhom19.subsystem.InterbankSubsystem;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class TransactionDaoTest {
    private final TransactionDao transactionDao = new TransactionDaoImp();
    private final InterbankInterface interbankInterface = new InterbankSubsystem();
    @BeforeEach
    void setUp() throws SQLException {
        DBHelper.initConnection();
    }
    @Test
    void createTransactionTest() throws IOException, SQLException {
        Card card = new Card("118609_group19_2020", "Group 19", "907", "1125");
        TransactionResponse transactionResponse = interbankInterface.refund(card, BigDecimal.valueOf(1000.00), "Hello");
        Transaction transaction = new Transaction(transactionResponse.getTransaction(), card.getCardCode());
        this.transactionDao.create(transaction);
        System.out.println(transaction.getId());
        DBHelper.commit();
    }

}
