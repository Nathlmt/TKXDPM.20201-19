package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.util.ResourceBundle;

import static org.tkxdpm20201.Nhom19.utils.Constants.BIKE_INFO_PATH;

public class BikeInfo implements Initializable {
    private static final BaseScreenHandler bikeInfoHandler = new BaseScreenHandler(BIKE_INFO_PATH);
    @FXML
    private Label name, licensePlate, bikeCode, battery, price, type, updateAt;
    @FXML
    private AnchorPane bikeInfoAnchor;

    public static BaseScreenHandler getBikeInfoHandler() {
        return bikeInfoHandler;
    }
    void displayBikeInfo() {
        Bike bike = (Bike) bikeInfoHandler.getEntityData();
        name.setText(bike.getName());
        licensePlate.setText( bike.getLicensePlate());
        bikeCode.setText(bike.getId().toString());
        price.setText(bike.getPrice().toString() + " đ");
        type.setText(bike.getType());
//        updateAt.setText(bike.getLastUpdate().toString());
    }
    public void handleButton1Action() {
        BaseScreenHandler paymentHandler = PaymentForm.getPaymentFormHandler();
        paymentHandler.setEntityData(bikeInfoHandler.getEntityData());
        Stage bikeInfo = (Stage) bikeInfoAnchor.getScene().getWindow();
        paymentHandler.setScreenStage(bikeInfo);
        paymentHandler.setPreviousScreen(bikeInfoHandler);
        paymentHandler.show();
    }
    public void back() {
        bikeInfoHandler.getPreviousScreen().show();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayBikeInfo();
    }
}
