package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.business.api.TransactionApi;
import org.tkxdpm20201.Nhom19.persistence.daos.DBHelper;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;


public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 660;

    public static void main(String[] args) {

        testAPI();
//        StationDao stationDao = new StationDaoImp();
//        try {
//            stationDao.getAll();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home_screen.fxml"));
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

    private static void testAPI(){
        TransactionApi transactionApi = new TransactionApi();

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(123));
        transactionRequest.setCardCode("118609_group19_2020");
        transactionRequest.setCvvCode("907");
        transactionRequest.setDateExpired("1125");
        transactionRequest.setOwner("Group 19");
        transactionRequest.setTransactionContent("test json");
        transactionRequest.setCreateAt(new Date().toString());
        try {
            transactionApi.processTransaction(transactionRequest);
        } catch (IOException e) {
            System.out.println("lỗi rồi ae ơi!!!!!!");
            e.printStackTrace();
        }
    }


}