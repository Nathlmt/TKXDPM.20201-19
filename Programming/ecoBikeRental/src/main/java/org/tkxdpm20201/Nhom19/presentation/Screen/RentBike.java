package org.tkxdpm20201.Nhom19.presentation.Screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import org.tkxdpm20201.Nhom19.presentation.Screen.BikeInfo;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.tkxdpm20201.Nhom19.utils.Constants.RENT_BIKE_PATH;

public class RentBike implements Initializable {
    private static final BaseScreenHandler rentBikeHandler = new BaseScreenHandler(RENT_BIKE_PATH);
    private final RentBikeController rentBikeController = new RentBikeController();

    private BikeDaoImp bikeDao = new BikeDaoImp();
    @FXML
    private AnchorPane rentBikeScreenAnchor;
    @FXML
    private TextField bikeCode;

    @FXML
    private void handleButton1Action(ActionEvent event) throws SQLException {
        Bike bike = rentBikeController.getBikeInfo(Integer.parseInt(bikeCode.getText()));
        Stage rentBikeStage = (Stage) rentBikeScreenAnchor.getScene().getWindow();
        BaseScreenHandler bikeInfoHandler = BikeInfo.getBikeInfoHandler();
        bikeInfoHandler.setPreviousScreen(rentBikeHandler);
        bikeInfoHandler.setScreenStage(rentBikeStage);
        bikeInfoHandler.setEntityData(bike);
        bikeInfoHandler.show();
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
