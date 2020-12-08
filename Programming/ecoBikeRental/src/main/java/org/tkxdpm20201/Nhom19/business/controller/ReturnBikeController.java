package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.business.api.HandleErrorResponse;
import org.tkxdpm20201.Nhom19.business.api.InterBankApiSystem;
import org.tkxdpm20201.Nhom19.business.api.Notification;
import org.tkxdpm20201.Nhom19.business.api.TransactionApiImp;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.TransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReturnBikeController {

    private final StationDao stationDao;
    private final BikeDao bikeDao;
    private final TransactionDao transactionDao;
    private final InterBankApiSystem interBankApiSystem;


    public ReturnBikeController() {
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


    public Notification returnBike(Station station) {
        RentingBike rentingBike = RentBikeController.getRentingBike();
        if (rentingBike != null) {
            Bike bikeReturn = rentingBike.getBike();
            LocalDateTime startDate = rentingBike.getStartDate();
            BigDecimal deposit = rentingBike.getDeposit();
            BigDecimal rentingFee = calculateFees(deposit, startDate, java.time.LocalDateTime.now());
            TransactionRequest transactionRequest = createTransactionRequest(rentingBike, deposit, rentingFee);

            try {
                TransactionResponse transactionResponse  = interBankApiSystem.processTransaction(transactionRequest);

                Notification notification = HandleErrorResponse.handle(transactionResponse.getErrorCode());
                if(notification.isStatus()){
                    handleStationReceiveBike(station, bikeReturn);
                }
                return notification;
            } catch (IOException e) {
                e.printStackTrace();
                return new Notification(false, "Request Error!");
            }
        }

        return new Notification(false, "haven't rented Bike yet");
    }

    private void handleStationReceiveBike(Station station, Bike bikeReturn){
        try {
           int resBike = bikeDao.updateCurrentStation(bikeReturn.getId(), station.getId());
            station.setAvailableBike(station.getAvailableBike() + 1);
            station.setAvailableRack(station.getAvailableRack() - 1);
            int resStation = stationDao.update(station);
            //TODO: xử lý nếu trả về lỗi
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }


    }


    private TransactionRequest createTransactionRequest(RentingBike rentingBike, BigDecimal deposit, BigDecimal rentingFee){
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionRequestToReturnBike(rentingBike, deposit, rentingFee);

        return null;
    }



    //TODO: nếu sau này có yêu cầu thêm cách tính phí thì sẽ thêm String: chứa chuỗi biểu thị cách tính phí
    private BigDecimal calculateFees(BigDecimal deposit, LocalDateTime startDate, LocalDateTime endDate){


        return null;
    }



}
