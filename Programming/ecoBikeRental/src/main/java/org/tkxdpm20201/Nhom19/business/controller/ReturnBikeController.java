package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFee;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFeeImp;
import org.tkxdpm20201.Nhom19.data.daos.*;
import org.tkxdpm20201.Nhom19.data.daos.implement.*;
import org.tkxdpm20201.Nhom19.data.entities.*;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.entities.bike.ElectricBike;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.exception.UnrecognizedException;
import org.tkxdpm20201.Nhom19.subsystem.InterbankInterface;
import org.tkxdpm20201.Nhom19.subsystem.InterbankSubsystem;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.DateUtil;
import org.tkxdpm20201.Nhom19.utils.Evaluation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ReturnBikeController extends BaseController {

    private final StationDao stationDao;
    private final BikeDao bikeDao;
    private final TransactionDao transactionDao;
    private final InterbankInterface interBankApiSystem;
    private final RentalDao rentalDao;
    private final RentalTransactionDao rentalTransactionDao;
    private  final CalculateFee calculateFee;


    public ReturnBikeController() {
        this.rentalDao = new RentalDaoImp();
        this.stationDao = new StationDaoImp();
        this.bikeDao = new BikeDaoImp();
        this.transactionDao = new TransactionDaoImp();
        this.rentalTransactionDao = new RentalTransactionDaoImp();
        this.interBankApiSystem = new InterbankSubsystem();
        this.calculateFee = new CalculateFeeImp();

    }

    public List<Station> getStationList() throws SQLException {
        List<Station> stationList = stationDao.getAll();
        System.out.println(stationList.size());
        return stationList;
    }

    /**
     *  carry out logics of return bike business
     * @param station: station which user want to return bike
     * @return
     */
    public void returnBike(Station station) {
        Timestamp localDateTimeEnd = DateUtil.toTimestamp(java.time.LocalDateTime.now());
        RentingBike rentingBike = Caching.getInstance().getRentingBike();
        if (rentingBike != null) {
            Rental rental = rentingBike.getRental();
            Bike bikeReturn = rentingBike.getBike();
            Card card = rentingBike.getCard();
            Timestamp startDate = rentingBike.getStartDate();
            BigDecimal deposit = rentingBike.getDeposit();
            BigDecimal rentFee = calculateFee.run(startDate, localDateTimeEnd, bikeReturn);
            BigDecimal amount = calculateAmount(deposit, rentFee);

            rental.setReturnStationId(station.getId());
            rental.setTimeEnd(localDateTimeEnd);
            rental.setStatus(Constants.RETURNED_BIKE);

            try {
                TransactionResponse transactionResponse;
                if(amount.compareTo(BigDecimal.ZERO) < 0)
                    transactionResponse = interBankApiSystem.refund(card, amount.abs(), "Hoàn tiền trả xe");
                else
                    transactionResponse  = interBankApiSystem.pay(card, amount, "Thu thêm tiền thuê xe");

                boolean b1 = handleStationReceiveBike(station, bikeReturn);
                boolean b2 = saveTransaction(rental, transactionResponse, card.getCardCode());
                if(b1 && b2){
                    Caching.getInstance().resetCache();
                    System.out.println("reset Cache!");
                }
                else
                    throw new UnrecognizedException(); // TODO: DB Exception
            } catch (IOException e) {
                e.printStackTrace();
                throw new UnrecognizedException();
            }
        }
    }


    /**
     * save transaction to System's Database
     * @param rental
     * @param transactionResponse
     * @param cardCode
     * @return true if success, otherwise false
     */
    private boolean saveTransaction(Rental rental, TransactionResponse transactionResponse, String cardCode){
        Transaction transaction = new Transaction(transactionResponse.getTransaction(), cardCode);
        try {
            transactionDao.create(transaction);
            rentalDao.update(rental);
            RentalTransaction rentalTransaction = new RentalTransaction(rental.getId(), transaction.getId());
            rentalTransactionDao.create(rentalTransaction);
            DBHelper.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * update info Bike and Station when return Bike
     * @param station
     * @param bikeReturn
     * @return true if success, otherwise false
     */
    private boolean handleStationReceiveBike(Station station, Bike bikeReturn){
        try {
           bikeDao.updateCurrentStation(bikeReturn.getId(), station.getId());
           stationDao.update(station);
           DBHelper.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }


    private BigDecimal calculateAmount(BigDecimal deposit, BigDecimal rentedFee){
        BigDecimal sub = rentedFee.subtract(deposit);
        System.out.println("rentedFee - deposit = " + sub);
        return sub;
    }




}
