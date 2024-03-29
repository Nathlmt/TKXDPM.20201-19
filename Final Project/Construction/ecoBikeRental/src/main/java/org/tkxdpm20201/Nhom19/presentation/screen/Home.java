package org.tkxdpm20201.Nhom19.presentation.screen;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFee;
import org.tkxdpm20201.Nhom19.business.caculateFee.CalculateFeeImp;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.model.Caching;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import org.tkxdpm20201.Nhom19.presentation.dialog.BaseDialog;
import org.tkxdpm20201.Nhom19.presentation.dialog.ErrorDialog;
import org.tkxdpm20201.Nhom19.presentation.dialog.NotificationDialog;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import static org.tkxdpm20201.Nhom19.utils.Constants.HOME_PATH;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private Timeline animation;
    private final RentBikeController rentBikeController = new RentBikeController();
    private long time;
    @FXML
    private Label rentedTime;

    @FXML
    private Label fee;

    @FXML
    private Label typeLabel;

    @FXML
    private Label nameBikeLabel;

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
            if(!Caching.getInstance().getStatus()){
                BaseScreenHandler rentBikeHandler = RentBike.getRentBikeHandler();
                goToStage(rentBikeHandler, homeScreenStage);
            }
            else{
                BaseDialog alert = new NotificationDialog("Bạn đang thuê xe! không thể thuê xe khác!");
                alert.show();
            }

        }
    }

    private void plusTime() {
        time++;
        LocalTime lt = LocalTime.MIN.plusSeconds( time  ) ;
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "HH:mm:ss" ) ;
        String timeFormated = lt.format( f );
        rentedTime.setText(timeFormated);
        if (time%60 == 0) {
//            Double currentFee = caculator.run(Caching.getInstance().getRentingBike().getBike(), time * 1000L);
            Double currentFee = Caching.getInstance().getRentingBike().getBike().calculateFee(time*1000L);
            fee.setText(String.valueOf(currentFee) + " đồng");
        }
    }

    private void clock() {
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->plusTime()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private long getEpochTime(){
        Timestamp now = DateUtil.toTimestamp(java.time.LocalDateTime.now());
        Timestamp startDate = Caching.getInstance().getRentingBike().getStartDate();
        return (now.getTime() - startDate.getTime());
    }

    public void returnBikeSuccessfully() {
        rentingBikeInfoPane.setDisable(true);
        rentingBikeInfoPane.setVisible(false);
    }

    public static BaseScreenHandler getHomeScreenHandler() {
        return homeScreenHandler;
    }

    public void setRentingBike(){
        RentingBike rentingBike = Caching.getInstance().getRentingBike();
        Bike bike = rentingBike.getBike();
        nameBikeLabel.setText(bike.getName());
        typeLabel.setText(bike.getType());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rentBikeController.checkStatusRentalBike();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            ErrorDialog errorDialog = new ErrorDialog("Lỗi DB, thử lại sau!");
            errorDialog.show();
        }
        if(!Caching.getInstance().getStatus()){
            rentingBikeInfoPane.setVisible(false);
        } else {
            setRentingBike();
            this.time = getEpochTime()/1000;
//            Double currentFee = caculator.run(Caching.getInstance().getRentingBike().getBike(), time * 1000L);
            Double currentFee = Caching.getInstance().getRentingBike().getBike().calculateFee(time * 1000L);
            fee.setText(String.valueOf(currentFee) + " đồng");
            clock();
        }
    }
}
