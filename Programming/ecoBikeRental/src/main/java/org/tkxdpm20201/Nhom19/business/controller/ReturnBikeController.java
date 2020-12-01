package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.persistence.daos.StationDao;
import org.tkxdpm20201.Nhom19.persistence.daos.implement.StationDaoImp;
import org.tkxdpm20201.Nhom19.persistence.entities.Bike;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;
import org.tkxdpm20201.Nhom19.persistence.model.RentingBike;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnBikeController {

    private final StationDao stationDao;


    public ReturnBikeController() {
        this.stationDao = new StationDaoImp();

    }


    public List<Station> getStationList() throws SQLException {
        List<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "Quoc oai", "hanoi", "12 m2", 12, 12, "active", new Date()));
        stationList.add(new Station(1, "Quoc oai", "hanoi", "12 m2", 12, 12, "active", new Date()));
        stationList.add(new Station(1, "Quoc oai", "hanoi", "12 m2", 12, 12, "active", new Date()));
        stationList.add(new Station(1, "Quoc oai", "hanoi", "12 m2", 12, 12, "active", new Date()));

        stationDao.getAll(); //TODO: Chỗ này sẽ phải sửa lại trong thiết kế là lấy stationList ra từ Dao chứ ko phải Entity nữa
        return stationList;
    }

    public boolean returnBike(Station station, Bike bike) {
        RentingBike rentingBike = RentBikeController.getRentingBike();
        if (rentingBike != null) {
            LocalDateTime startDate = rentingBike.getStartDate();
            BigDecimal deposit = new BigDecimal(bike.getPrice().intValue() * Constants.DEPOSIT_PERCENT);
            BigDecimal rentingFee = calculateFees(deposit, startDate, java.time.LocalDateTime.now());

            // TODO: requestReturnBikeTransaction()


            return true;
        }
        return true;
    }

    //TODO: nếu sau này có yêu cầu thêm cách tính phí thì sẽ thêm String: chứa chuỗi biểu thị cách tính phí
    private BigDecimal calculateFees(BigDecimal deposit, LocalDateTime startDate, LocalDateTime endDate){

        return null;
    }



}
