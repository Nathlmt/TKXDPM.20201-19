package org.tkxdpm20201.Nhom19.presentation.screen;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import static org.tkxdpm20201.Nhom19.utils.Constants.HOME_PATH;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private Label rentedTime;

    @FXML
    private Label fee;

    @FXML
    private Label status;

    @FXML
    private ImageView bikeImageView;

    @FXML
    private Pane rentingBikeInfoPane;

    @FXML
    private JFXButton btnFindStation;

    @FXML
    private JFXButton btnReturnBike;
    @FXML
    private JFXButton btnRentBike;
    @FXML
    private AnchorPane homeScreenAnchor;
    private final static BaseScreenHandler homeScreenHandler  = new BaseScreenHandler(HOME_PATH);

    private Stage setupHomeScreenHandler() {
        Stage homeScreenStage = (Stage) homeScreenAnchor.getScene().getWindow();
        homeScreenHandler.setScreenStage(homeScreenStage);
        return homeScreenStage;
    }
    private void goToStage(BaseScreenHandler nextHandler, Stage previousStage) {
        nextHandler.setScreenStage(previousStage);
        nextHandler.setPreviousScreen(homeScreenHandler);
        nextHandler.show();
    }
    public void handleButtonClicks(ActionEvent mouseEvent) {
        Stage homeScreenStage = setupHomeScreenHandler();
        if (mouseEvent.getSource() == btnFindStation) {
            BaseScreenHandler findStationHandler = FindStation.getFindStationHandler();
            goToStage(findStationHandler, homeScreenStage);
        };
        if (mouseEvent.getSource() == btnReturnBike) {
            BaseScreenHandler returnBikeHandler = ReturnBike.getReturnBikeHandler();
            goToStage(returnBikeHandler, homeScreenStage);
        };
        if (mouseEvent.getSource() == btnRentBike) {
            BaseScreenHandler rentBikeHandler = RentBike.getRentBikeHandler();
            goToStage(rentBikeHandler, homeScreenStage);
        }
    }


    public void returnBikeSuccessfully() {
        rentingBikeInfoPane.setDisable(true);
        rentingBikeInfoPane.setVisible(false);
    }

    public static BaseScreenHandler getHomeScreenHandler() {
        return homeScreenHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!Caching.getInstance().getStatus()){
            rentingBikeInfoPane.setVisible(false);
        }
    }
}
