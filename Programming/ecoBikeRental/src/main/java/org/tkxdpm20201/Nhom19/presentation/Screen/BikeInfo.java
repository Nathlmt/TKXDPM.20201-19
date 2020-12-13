package org.tkxdpm20201.Nhom19.presentation.Screen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.util.ResourceBundle;

import static org.tkxdpm20201.Nhom19.utils.Constants.BIKE_INFO_PATH;

public class BikeInfo implements Initializable {
    private static final BaseScreenHandler bikeInfoHandler = new BaseScreenHandler(BIKE_INFO_PATH);
    @FXML
    private Label name, licensePlate, bikeCode, battery, price, type, updateAt;
    public static BaseScreenHandler getBikeInfoHandler() {
        return bikeInfoHandler;
    }
    void displayBikeInfo() {
        Bike bike = (Bike) bikeInfoHandler.getEntityData();
        System.out.println(bike.getType());
        name.setText(bike.getName());
        licensePlate.setText(bike.getLicensePlate());
        bikeCode.setText(bike.getId().toString());
        price.setText(bike.getPrice().toString());
        type.setText(bike.getType());
//        updateAt.setText(bike.getLastUpdate().toString());
    }
    public void handleButton1Action() {

    }
    public void back() {
        bikeInfoHandler.getPreviousScreen().show();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayBikeInfo();
    }
}
