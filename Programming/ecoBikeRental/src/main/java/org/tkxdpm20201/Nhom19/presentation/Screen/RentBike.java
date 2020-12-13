package org.tkxdpm20201.Nhom19.presentation.Screen;

import javafx.fxml.Initializable;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.util.ResourceBundle;

import static org.tkxdpm20201.Nhom19.utils.Constants.RENT_BIKE_PATH;
import static org.tkxdpm20201.Nhom19.utils.Constants.RETURN_BIKE_PATH;

public class RentBike implements Initializable {
    private static final BaseScreenHandler rentBikeHandler = new BaseScreenHandler(RENT_BIKE_PATH);

    public static BaseScreenHandler getRentBikeHandler() {
        return rentBikeHandler;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void returnHome() {
        rentBikeHandler.getPreviousScreen().show();
    }

}
