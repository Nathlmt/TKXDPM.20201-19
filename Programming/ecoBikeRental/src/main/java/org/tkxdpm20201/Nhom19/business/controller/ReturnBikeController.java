package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.business.api.HandleErrorResponse;
import org.tkxdpm20201.Nhom19.business.api.InterBankApiSystem;
import org.tkxdpm20201.Nhom19.business.api.Notification;
import org.tkxdpm20201.Nhom19.business.api.TransactionApiImp;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.TransactionDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.entities.Transaction;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReturnBikeController extends BaseController {

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


    public Notification returnBike(Station station) {
        LocalDateTime localDateTimeEnd = java.time.LocalDateTime.now();
        RentingBike rentingBike = Caching.getInstance().getRentingBike();
        if (rentingBike != null) {
            Rental rental = rentingBike.getRental();
            Bike bikeReturn = rentingBike.getBike();
            LocalDateTime startDate = rentingBike.getStartDate();
            BigDecimal deposit = rentingBike.getDeposit();
            BigDecimal rentingFee = calculateFees(deposit, startDate, localDateTimeEnd);
            TransactionRequest transactionRequest = createTransactionRequest(rentingBike, deposit, rentingFee);

            rental.setReturnStationId(station.getId());
//            rental.setTimeEnd(DateUtil.format(localDateTimeEnd));
            rental.setStatus(Constants.RETURNED_BIKE);

            try {
                TransactionResponse transactionResponse  = interBankApiSystem.processTransaction(transactionRequest);

                Notification notification = HandleErrorResponse.handle(transactionResponse.getErrorCode());
                if(notification.isStatus()){
                    boolean b1 = handleStationReceiveBike(station, bikeReturn);
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


    private TransactionRequest createTransactionRequest(RentingBike rentingBike, BigDecimal deposit, BigDecimal rentingFee){
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionRequestToReturnBike(rentingBike, deposit, rentingFee);

        return null;
    }

    //TODO: nếu sau này có yêu cầu thêm cách tính phí thì sẽ thêm String: chứa chuỗi biểu thị cách tính phí
    private BigDecimal calculateFees(BigDecimal deposit, LocalDateTime startDate, LocalDateTime endDate){
        long epoch = DateUtil.subtractTime(startDate, endDate);
        System.out.println("time Second: " + epoch/ 1000L);

        return null;
    }



}
