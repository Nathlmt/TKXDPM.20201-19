package org.tkxdpm20201.Nhom19.subsystem.interbank;

import org.apache.http.HttpResponse;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.exception.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import static  org.tkxdpm20201.Nhom19.subsystem.interbank.Config.REFUND;
import static  org.tkxdpm20201.Nhom19.subsystem.interbank.Config.PAY;

/**
 * @author lytuan
 * @author letuan(refactor)
 * Controller cho api interbank
 */
public class InterbankController {
    private static final InterbankBoundary interbankBoundary = new InterbankBoundary();

    /**
     * Ham gui transactions
     * @param transactionRequest
     * @return TransactionResponse
     * @throws PaymentException,IOException
     */
    private TransactionResponse processTransaction(TransactionRequest transactionRequest) throws IOException {
        HttpResponse httpResponse = interbankBoundary.requestHTTP(transactionRequest);
        TransactionResponse transactionResponse =  interbankBoundary.getTransactionResponse(httpResponse);
        switch (transactionResponse.getErrorCode()) {
            case "00":
                break;
            case "01":
                throw new InvalidCardException();
            case "02":
                throw new NotEnoughBalanceException();
            case "03":
                throw new InternalServerErrorException();
            case "04":
                throw new SuspiciousTransactionException();
            case "05":
                throw new NotEnoughTransactionInfoException();
            case "06":
                throw new InvalidVersionException();
            case "07":
                throw new InvalidTransactionAmountException();
            default:
                throw new UnrecognizedException();
        }
        return transactionResponse;
    };

    /**
     * Dung de thanh toan
     * @param card: thong tin the
     * @param amount: so tien giao dich
     * @param content: noi dung giao dich
     * @return TransactionResponse
     * @throws IOException, PaymentException
     */
    public TransactionResponse pay(Card card, BigDecimal amount, String content) throws IOException {
        Date currentDate = new Date();
        TransactionRequest payRequest = new TransactionRequest(content, amount,currentDate, card);
        payRequest.setCommand(PAY);
        return processTransaction(payRequest);
    }

    /**
     * Dung de tra lai tien cho user
     * @param card
     * @param amount
     * @param content
     * @return TransactionResponse
     * @throws PaymentException: neu thanh toan loi tra ve cac loi thanh toan
     */
    public TransactionResponse refund(Card card, BigDecimal amount, String content) throws  IOException {
        Date currentDate = new Date();
        TransactionRequest refundRequest = new TransactionRequest(content, amount,currentDate, card);
        refundRequest.setCommand(REFUND);
        return processTransaction(refundRequest);
    }
    /**
     * 
     */
    public Object resetBalance() {
        return null;
    }

}
