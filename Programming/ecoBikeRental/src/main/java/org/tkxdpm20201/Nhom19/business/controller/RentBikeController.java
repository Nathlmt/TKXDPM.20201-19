package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.*;
import org.tkxdpm20201.Nhom19.data.daos.implement.*;
import org.tkxdpm20201.Nhom19.data.entities.*;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.exception.BikeNotAvailableException;
import org.tkxdpm20201.Nhom19.exception.EcobikeException;
import org.tkxdpm20201.Nhom19.exception.InvalidBikeCodeException;
import org.tkxdpm20201.Nhom19.exception.PaymentException;
import org.tkxdpm20201.Nhom19.subsystem.InterbankInterface;
import org.tkxdpm20201.Nhom19.subsystem.InterbankSubsystem;
import org.tkxdpm20201.Nhom19.utils.Constants;

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
    private final BikeDao bikeDao = new BikeDaoImp();

    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station) {

    }

    public Bike getBikeInfo(int bikeCode) throws SQLException, EcobikeException {
        Bike bike =  bikeDao.getBikeById(bikeCode);
        if (bike == null) {
            throw new InvalidBikeCodeException();
        };
//        if (!bike.getStatus().equals("available")) {
//            throw new BikeNotAvailableException();
//        }
        return bike;
    }

    /**
     * Thuc thi sau khi nguoi dung bam thanh toan
     *
     * @param bike:
     * @param customerId:    id cua khach hang
     * @param rentStationId: id cua tram thue xe
     */
    public void handleRentBike(Bike bike, int customerId,
                               int rentStationId,
                               BigDecimal price,
                               Card card,
                               String content) throws SQLException, IOException, PaymentException {
        //validate content
        String contentAfterCheck = validateContent(content);
        //query api to pay deposit
        InterbankInterface interbankSubsystem = new InterbankSubsystem();
        TransactionResponse transactionPay = interbankSubsystem.pay(card, price, contentAfterCheck);
        //save or update renting operion data (update bike, station,....)
        RentingBike rentingBike  = saveRentingData(bike, customerId, rentStationId, card, transactionPay);
        //update bike caches
        Caching.getInstance().setRentingBike(rentingBike, Constants.RENTING_STATUS);
    }

    /**
     * update/create data into database
     * @param bike: bike object
     * @param customerId: customer id
     * @param rentStationId: rent station id
     * @param card: card pbject
     * @param transactionPay: transaction Response
     * @throws SQLException
     */
    private RentingBike saveRentingData(Bike bike, int customerId, int rentStationId, Card card, TransactionResponse transactionPay) throws SQLException {
        //create rental (rental table save data about renting operation)
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        Rental rental = new Rental(bike.getId(), customerId, rentStationId, "renting", startTime);
        RentalDao rentalDao = new RentalDaoImp();
        rentalDao.create(rental);
        //update table bike (Change status bike from available to renting)
        bikeDao.updateStatusBike(bike.getId(), "renting");
        //update table station (Change station status)
        StationDao stationDao = new StationDaoImp();
        stationDao.updateStationWhenRentBike(rentStationId);
        // save Transaction (save all transaction when pay or refund)
        TransactionDao transactionDao = new TransactionDaoImp();
        Transaction transaction = new Transaction(transactionPay.getTransaction(), card.getCardCode());
        transactionDao.create(transaction);
        //update rental transaction table (table link rental, transaction)
        RentalTransactionDao rentalTransactionDao = new RentalTransactionDaoImp();
        RentalTransaction rentalTransaction = new RentalTransaction(rental.getId(), transaction.getId());
        //commit database
        DBHelper.commit();
        return new RentingBike(bike, card, rental);
    }

    /**
     *
     * @param content
     * @return
     */
    private String validateContent(String content) {
        return "Trả tiền cọc:" +content;
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
