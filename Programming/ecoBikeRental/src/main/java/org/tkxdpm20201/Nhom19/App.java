package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.business.api.TransactionApiImp;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;


public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 660;

    public static void main(String[] args) {
//        StationDao stationDao = new StationDaoImp();

//        testAPI();
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

    private RentingBike getRentingBikeInfo(){
        if(RentBikeController.getRentingBike() == null){
            // TODO: thực hiện các lệnh truy vấn get info ở đây
        }
        return null;
    }

    private static void testAPI(){
        TransactionApiImp transactionApiImp = new TransactionApiImp();

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(100));
        transactionRequest.setCardCode("118609_group5_2020");
        transactionRequest.setCvvCode("271");
        transactionRequest.setDateExpired("1125");
        transactionRequest.setOwner("Group 5");
        transactionRequest.setTransactionContent("Thanh toan thue xe dap");
        transactionRequest.setCreatedAt(new Date());
        try {
            TransactionResponse trans = transactionApiImp.processTransaction(transactionRequest);
        } catch (IOException e) {
            System.out.println("lỗi rồi ae ơi!!!!!!");
            e.printStackTrace();
        }
    }


}