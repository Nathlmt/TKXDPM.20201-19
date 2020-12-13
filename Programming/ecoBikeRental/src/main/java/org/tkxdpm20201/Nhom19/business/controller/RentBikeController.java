package org.tkxdpm20201.Nhom19.business.controller;

import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;

public class RentBikeController {

    /**
     * Caching information when renting bike
     */
    private static RentingBike rentingBike;

    public static RentingBike getRentingBike() {
        if(rentingBike == null){

            // TODO: khi rentingBike null thì phải truy vấn lên DB để tìm xem giao dịch gần nhất của khách hàng có giao dịch nào mà status của Rental là đang thuê xe hay không?
            //TODO: làm như thế để chắc chắn là ngay cả khi hệ thống sập khi KH đang sử dụng xe và khi khởi động lại thì rentingBike đảm bảo lại lấy được có dữ liệu ban đầu
            return null;
        }
        else
            return rentingBike;
    }

    public static void updateRental(Rental rental){
        rentingBike.setRental(rental);
    }


    /**
     *
     * @param transactionRequest
     * @param bike
     * @param station
     */
    public void handleBikeInfo(TransactionRequest transactionRequest, Bike bike, Station station){

    }


}
