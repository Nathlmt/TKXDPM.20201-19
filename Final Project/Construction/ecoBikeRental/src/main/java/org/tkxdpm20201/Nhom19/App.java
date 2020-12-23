package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.CardDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.RentalDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.CardDaoImp;
import org.tkxdpm20201.Nhom19.data.daos.implement.RentalDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.Rental;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;

import static org.tkxdpm20201.Nhom19.utils.Constants.HOME_PATH;

public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 660;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        DBHelper.initConnection();
        Parent root = FXMLLoader.load(getClass().getResource(HOME_PATH));
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        setSizeForWindow(stage);
        stage.setResizable(false);
        stage.show();

    }

    public static void setSizeForWindow(Stage stage){
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }



}