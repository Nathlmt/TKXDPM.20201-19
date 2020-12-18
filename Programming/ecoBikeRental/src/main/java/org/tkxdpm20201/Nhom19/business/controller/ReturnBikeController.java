package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.business.api.*;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.TransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.*;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.DateUtil;
import org.tkxdpm20201.Nhom19.utils.Evaluation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ReturnBikeController {

    private final StationDao stationDao;
    private final BikeDao bikeDao;
    private final TransactionDao transactionDao;
    private final InterBankApiSystem interBankApiSystem;
    private final RentalDao rentalDao;


    public ReturnBikeController() {
        this.rentalDao = new RentalDaoImp();
        this.stationDao = new StationDaoImp();
        this.bikeDao = new BikeDaoImp();
        this.transactionDao = new TransactionDaoImp();
        this.interBankApiSystem = new TransactionApiImp();

    }

    public List<Station> getStationList() throws SQLException {
        List<Station> stationList = stationDao.getAll();
        System.out.println(stationList.size());
        return stationList;
    }


    /**
     *
     * @param station: station which user want to return bike
     * @return Notification
     */
    public Notification returnBike(Station station) {
        Timestamp localDateTimeEnd = DateUtil.toTimestamp(java.time.LocalDateTime.now());
        RentingBike rentingBike = Caching.getInstance().getRentingBike();
        if (rentingBike != null) {
            Rental rental = rentingBike.getRental();
            Bike bikeReturn = rentingBike.getBike();
            Timestamp startDate = rentingBike.getStartDate();
            BigDecimal deposit = rentingBike.getDeposit();
            BigDecimal rentFee = calculateFees(startDate, localDateTimeEnd, bikeReturn);
            BigDecimal amount = calculateAmount(deposit, rentFee);
            TransactionRequest transactionRequest;
            if(amount.compareTo(BigDecimal.ZERO) < 0)
                transactionRequest = createTransactionRequest(rentingBike, amount.abs(), Config.REFUND);
            else
                transactionRequest = createTransactionRequest(rentingBike, amount, Config.PROCESS_TRANS);

            rental.setReturnStationId(station.getId());
            rental.setTimeEnd(localDateTimeEnd);
            rental.setStatus(Constants.RETURNED_BIKE);

            try {
                TransactionResponse transactionResponse  = interBankApiSystem.processTransaction(transactionRequest);

                Notification notification = HandleErrorResponse.handle(transactionResponse.getErrorCode());
                if(notification.isStatus()){
                    boolean b1 = handleStationReceiveBike(station, bikeReturn); // TODO: Bắt đầu từ đây.. đoạn trên OK rồi
                    boolean b2 = saveTransaction(rental, transactionResponse, rentingBike.getCardId());
                    if(b1 && b2){
                        Caching.getInstance().resetCache();
                        System.out.println("reset Cache!");
                        return new Notification(true, "Giao dịch thành công!");
                    }
                    else
                        return new Notification(false, "Server DB Lỗi @@");
                }
                return notification;
            } catch (IOException e) {
                e.printStackTrace();
                return new Notification(false, "Server lỗi @@");
            }
        }

        return new Notification(false, "Bạn chưa thuê xe!");
    }


    private boolean saveTransaction(Rental rental, TransactionResponse transactionResponse, int cardCode){
        Transaction transaction = new Transaction(transactionResponse.getTransaction(), cardCode);
        try {
            transactionDao.create(transaction);
            rentalDao.update(rental);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean handleStationReceiveBike(Station station, Bike bikeReturn){
        boolean resBike = false;
        boolean resStation = false;
        try {
           resBike = bikeDao.updateCurrentStation(bikeReturn.getId(), station.getId());
           resStation = stationDao.update(station);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return resBike & resStation;
    }


    private TransactionRequest createTransactionRequest(RentingBike rentingBike, BigDecimal amount, String command){
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionRequestToReturnBike(rentingBike, amount, command);
        return transactionRequest;
    }

    private BigDecimal calculateFees(Timestamp startDate, Timestamp endDate, Bike bike){
        long epoch = endDate.getTime() - startDate.getTime();
        double rentFee = 0;
        System.out.println("time second rented: " + epoch/ 1000L);
        long minutes = epoch/(1000*60);
        rentFee = 10;
        if(minutes > 30){
            long count = minutes/15 + 1;
            rentFee += count*3;
        }
        if(bike instanceof ElectricBike)
            rentFee = rentFee*1.5;

        return new BigDecimal(rentFee);
    }

    private BigDecimal calculateAmount(BigDecimal deposit, BigDecimal rentedFee){
        BigDecimal sub = rentedFee.subtract(deposit);
//        return sub;
        return new BigDecimal(-123);
    }




}
