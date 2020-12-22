package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.*;
import org.tkxdpm20201.Nhom19.data.daos.implement.*;
import org.tkxdpm20201.Nhom19.data.entities.*;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.exception.PaymentException;
import org.tkxdpm20201.Nhom19.subsystem.InterbankInterface;
import org.tkxdpm20201.Nhom19.subsystem.InterbankSubsystem;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author tuan.lm
 */
public class RentBikeController extends BaseController {

    /**
     * Caching information when renting bike
     */
    private static RentingBike rentingBike;
    private BikeDao bikeDao = new BikeDaoImp();

    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station) {

    }

    public Bike getBikeInfo(int bikeCode) throws SQLException {
        return bikeDao.getBikeById(bikeCode);
    }

    /**
     * Thuc thi sau khi nguoi dung bam thanh toan
     *
     * @param bikeId:        id cua xe
     * @param customerId:    id cua khach hang
     * @param rentStationId: id cua tram thue xe
     */
    public void handleRentBike(int bikeId, int customerId,
                               int rentStationId,
                               BigDecimal price,
                               Card card,
                               String content) throws SQLException, IOException, PaymentException {
        //validate content
        String contentAfterCheck = validateContent(content);
        //query api interbank de tru tien dat coc
        InterbankInterface interbankSubsystem = new InterbankSubsystem();
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        TransactionResponse transactionPay = interbankSubsystem.pay(card, price, contentAfterCheck);
        //update table rental
        Rental rental = new Rental(bikeId, customerId, rentStationId, "renting", startTime);
        RentalDao rentalDao = new RentalDaoImp();
        rentalDao.create(rental);
        //update table bike (Trang thai cua xe)
        bikeDao.updateStatusBike(bikeId, "renting");
        //update table station (Trang thai cua tram)
        StationDao stationDao = new StationDaoImp();
        stationDao.updateStationWhenRentBike(rentStationId);
        // save Transaction
        TransactionDao transactionDao = new TransactionDaoImp();
        Transaction transaction = new Transaction(transactionPay.getTransaction(), card.getCardCode());
        transactionDao.create(transaction);
        //update rental transaction
        RentalTransactionDao rentalTransactionDao = new RentalTransactionDaoImp();
        RentalTransaction rentalTransaction = new RentalTransaction(rental.getId(), transaction.getId());
        //commit database
        DBHelper.commit();
    }
    /**
     *
     * @param content
     * @return
     */
    private String validateContent(String content) {
        if (content.isEmpty()) {
            return "RENTING BIKE";
        }
        return content;
    }

    /**
     *
     * @param card
     * @return
     */
    public boolean validateCartInfo(Card card) {
        if (validateCcv(card.getCvvCode())
                && validateOwner(card.getOwner())
                && validateExpDate(card.getDateExpired())
                && validateCardCode(card.getCardCode())
        ) {
            return true;
        }
        ;
        return false;
    }

    /**
     *
     * @param cardCode
     * @return
     */
    public boolean validateCardCode(String cardCode) {

        return true;
    }

    /**
     *
     * @param owner
     * @return
     */
    public boolean validateOwner(String owner) {

        return true;
    }

    /**
     *
     * @param cvvCode
     * @return
     */
    public boolean validateCcv(String cvvCode) {
        return true;
    }

    /**
     *
     * @param expDate
     * @return
     */
    public boolean validateExpDate(String expDate) {

        return true;
    }


}
