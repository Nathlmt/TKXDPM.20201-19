package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReturnBikeController {

    private final StationDao stationDao;


    public ReturnBikeController() {
        this.stationDao = new StationDaoImp();

    }

    public List<Station> getStationList() throws SQLException {
        List<Station> stationList = stationDao.getAll();
        return stationList;
    }

    public boolean returnBike(Station station) {
        RentingBike rentingBike = RentBikeController.getRentingBike();
        if (rentingBike != null) {
            Bike bikeReturn = rentingBike.getBike();
            LocalDateTime startDate = rentingBike.getStartDate();
            BigDecimal deposit = rentingBike.getDeposit();
            BigDecimal rentingFee = calculateFees(deposit, startDate, java.time.LocalDateTime.now());
            // TODO: requestReturnBikeTransaction()
            return true;
        }
        return true;
    }


    //TODO: nếu sau này có yêu cầu thêm cách tính phí thì sẽ thêm String: chứa chuỗi biểu thị cách tính phí
    private BigDecimal calculateFees(BigDecimal deposit, LocalDateTime startDate, LocalDateTime endDate) {


        return null;
    }


}
