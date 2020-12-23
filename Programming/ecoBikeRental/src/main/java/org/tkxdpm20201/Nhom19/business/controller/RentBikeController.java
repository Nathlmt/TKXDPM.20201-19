package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.*;
import org.tkxdpm20201.Nhom19.data.daos.implement.*;
import org.tkxdpm20201.Nhom19.data.entities.*;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;
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
 * Class user for renting operation
 * @author LeMinhTuan
 */
public class RentBikeController extends BaseController {
    private final StationDao stationDao;
    private final BikeDao bikeDao;
    private final TransactionDao transactionDao;
    private final InterbankInterface interBankApiSystem;
    private final RentalDao rentalDao;
    private final RentalTransactionDao rentalTransactionDao;



    public RentBikeController() {
        this.rentalDao = new RentalDaoImp();
        this.stationDao = new StationDaoImp();
        this.bikeDao = new BikeDaoImp();
        this.transactionDao = new TransactionDaoImp();
        this.rentalTransactionDao = new RentalTransactionDaoImp();
        this.interBankApiSystem = new InterbankSubsystem();

    }

    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station) {

    }

    /**
     * Get bike info when has bike code
     * @param bikeCode
     * @return bike
     * @throws SQLException
     * @throws EcobikeException: Throw exception when invalid bike code or bike not available
     */
    public Bike getBikeInfo(int bikeCode) throws SQLException, EcobikeException {
        Bike bike =  bikeDao.getById(bikeCode);
        if (bike == null) {
            throw new InvalidBikeCodeException();
        };
        if (!bike.getStatus().equals("available")) {
            throw new BikeNotAvailableException();
        }
        return bike;
    }

    /**
     * Run when user click payment
     *
     * @param bike:
     * @param customerId:
     * @param rentStationId:
     */
    public void handleRentBike(Bike bike, int customerId,
                               int rentStationId,
                               BigDecimal price,
                               Card card,
                               String content) throws SQLException, IOException, PaymentException {
        //validate content
        String contentAfterCheck = validateContent(content);
        //query api to pay deposit
        TransactionResponse transactionPay = interBankApiSystem.pay(card, price, contentAfterCheck);
        //save or update renting operion data (update bike, station,....)
        RentingBike rentingBike  = saveRentingData(bike, customerId, rentStationId, card, transactionPay);
        //update bike caches
        Caching.getInstance().setRentingBike(rentingBike, Constants.RENTING_STATUS);
    }

    /**
     * update/create data into database when renting
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
        rentalDao.create(rental);
        //update table bike (Change status bike from available to renting)
        bikeDao.updateStatusBike(bike.getId(), Constants.RENTING);
        //update table station (Change station status)
        stationDao.updateStationWhenRentBike(rentStationId);
        // save Transaction (save all transaction when pay or refund)
        Transaction transaction = new Transaction(transactionPay.getTransaction(), card.getCardCode());
        transactionDao.create(transaction);
        //update rental transaction table (table link rental, transaction)
        RentalTransaction rentalTransaction = new RentalTransaction(rental.getId(), transaction.getId());
        rentalTransactionDao.create(rentalTransaction);
        //commit database
        DBHelper.commit();
        return new RentingBike(bike, card, rental);
    }

    /**
     * validate transaction content
     * @param content: validate content transaction
     * @return String format like: "Trả tiền cọc" + content
     */
    private String validateContent(String content) {
        return "Trả tiền cọc:" +content;
    }

    /**
     *
     * @param card: card information
     * @return true if card is valid
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
     * @param cardCode:
     * @return: true if cardCode valid
     */
    public boolean validateCardCode(String cardCode) {

        return true;
    }

    /**
     * validate owner of card
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
