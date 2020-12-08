package org.tkxdpm20201.Nhom19.presentation.Screen;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private AnchorPane homeScreenAnchor;
    private final BaseScreenHandler homeScreenHandler  = new BaseScreenHandler(HOME_PATH);

    private Stage setupHomeScreenHandler() {
        Stage homeScreenStage = (Stage) homeScreenAnchor.getScene().getWindow();
        this.homeScreenHandler.setScreenStage(homeScreenStage);
        return homeScreenStage;
    }

    public void handleButtonClicks(ActionEvent mouseEvent) {
        Stage homeScreenStage = setupHomeScreenHandler();
        if (mouseEvent.getSource() == btnFindStation) {
            BaseScreenHandler findStationHandler = FindStation.getFindStationHandler();
            findStationHandler.setScreenStage(homeScreenStage);
            findStationHandler.setPreviousScreen(this.homeScreenHandler);
            findStationHandler.show();
        };
        if (mouseEvent.getSource() == btnReturnBike) {
            BaseScreenHandler returnBikeHandler = ReturnBike.getReturnBikeHandler();
            returnBikeHandler.setScreenStage(homeScreenStage);
            returnBikeHandler.setPreviousScreen(this.homeScreenHandler);
            returnBikeHandler.show();
        }
    }


    public void returnBikeSuccessfully() {
        rentingBikeInfoPane.setDisable(true);
        rentingBikeInfoPane.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
