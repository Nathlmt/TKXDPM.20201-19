package org.tkxdpm20201.Nhom19.business.api;

import org.tkxdpm20201.Nhom19.persistence.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionResponse;

import java.io.IOException;

public interface InterBankApiSystem {

    TransactionResponse processTransaction(TransactionRequest transactionRequest) throws IOException;

    Object resetBalance();
}
