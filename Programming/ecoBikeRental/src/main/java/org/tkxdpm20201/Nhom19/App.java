package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.tkxdpm20201.Nhom19.business.api.TransactionApi;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.exception.GlobalExceptionHandler;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.io.Console;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import static org.tkxdpm20201.Nhom19.utils.Constants.HOME_PATH;

public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 660;

    public static void main(String[] args) {
//        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);
        test();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource(HOME_PATH));
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        setSizeForWindow(stage);
        stage.setResizable(false);
        stage.show();
        DBHelper.initConnection();
    }

    public static void setSizeForWindow(Stage stage){
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    private static void test(){
        Rental rental = new Rental();
        rental.setStatus(Constants.RENTING_BIKE);
        rental.setBikeId(12);
        rental.setCustomerId(13);
        rental.setTimeStart(DateUtil.format(java.time.LocalDateTime.now()));
        rental.setId(1);

        Bike bike = new Bike();
        bike.setId(12);
        bike.setName("Xe test");
        bike.setPrice(new BigDecimal("1200000"));
        Card card = new Card(1, new BigDecimal("123"), "123", "Ly tuan", "111", "111234");
        RentingBike rentingBike = new RentingBike(bike, card, rental);
        Caching.getInstance().setRentingBike(rentingBike, Constants.RENTING_STATUS);
    }

//    private static void testAPI(){
//        TransactionApi transactionApi = new TransactionApi();
//
//        TransactionRequest transactionRequest = new TransactionRequest();
//        transactionRequest.setAmount(new BigDecimal(100));
//        transactionRequest.setCardCode("118609_group5_2020");
//        transactionRequest.setCvvCode("271");
//        transactionRequest.setDateExpired("1125");
//        transactionRequest.setOwner("Group 5");
//        transactionRequest.setTransactionContent("Thanh toan thue xe dap");
//        transactionRequest.setCreatedAt(new Date());
//        try {
//            TransactionResponse trans = transactionApi.processTransaction(transactionRequest);
////        } catch (IOException e) {
//            System.out.println("lỗi rồi ae ơi!!!!!!");
//            e.printStackTrace();
//        }
//    }


}