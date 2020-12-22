package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import org.tkxdpm20201.Nhom19.presentation.dialog.ErrorDialog;
import org.tkxdpm20201.Nhom19.utils.Constants;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RentBike implements Initializable {
    private static final BaseScreenHandler rentBikeHandler = new BaseScreenHandler(Constants.RENT_BIKE_PATH);
    private final RentBikeController rentBikeController = new RentBikeController();

    @FXML
    private AnchorPane rentBikeScreenAnchor;
    @FXML
    private TextField bikeCode;

    @FXML
    private void handleButton1Action(ActionEvent event) throws SQLException {
        try {
            Bike bike = rentBikeController.getBikeInfo(Integer.parseInt(bikeCode.getText()));
            Stage rentBikeStage = (Stage) rentBikeScreenAnchor.getScene().getWindow();
            BaseScreenHandler bikeInfoHandler = BikeInfo.getBikeInfoHandler();
            bikeInfoHandler.setPreviousScreen(rentBikeHandler);
            bikeInfoHandler.setScreenStage(rentBikeStage);
            bikeInfoHandler.setEntityData(bike);
            bikeInfoHandler.show();
        } catch (RuntimeException ex1) {
            ErrorDialog errorDialog =  new ErrorDialog(ex1.getMessage());
            errorDialog.show();
        }
    }

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
