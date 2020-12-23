package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.tkxdpm20201.Nhom19.business.controller.ReturnBikeController;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import org.tkxdpm20201.Nhom19.presentation.dialog.ErrorDialog;
import org.tkxdpm20201.Nhom19.presentation.dialog.NotificationDialog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.tkxdpm20201.Nhom19.utils.Constants.RETURN_BIKE_PATH;

public class ReturnBike implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;
    private final ReturnBikeController returnBikeController;
    private static final BaseScreenHandler returnBikeHandler = new BaseScreenHandler(RETURN_BIKE_PATH);

    public static BaseScreenHandler getReturnBikeHandler() {
        return returnBikeHandler;
    }

    public ReturnBike() {
        stationObservableList = FXCollections.observableArrayList();
        returnBikeController = new ReturnBikeController();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showStationList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            ErrorDialog errorDialog = new ErrorDialog("lỗi Database, xin lỗi vì sự bất tiện này!");
            errorDialog.show();
        }

    }

    private void showStationList() throws SQLException {
        List<Station> stationList = returnBikeController.getStationList();
        this.stationObservableList.addAll(stationList);
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStation());
    }

    @FXML
    public void returnHome(MouseEvent event) throws IOException {
        returnBikeHandler.getPreviousScreen().show();
    }

    @FXML
    public void chooseStation(MouseEvent event) throws IOException {

        Station station = listStation.getSelectionModel().getSelectedItem();
        if(station == null ){
            // DO NOTHING
            System.out.println("station click null");
        }
        else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Bạn có muốn trả xe ở "+ station.getStationName()+" ?");
            alert.setTitle("Confirm");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                returnBikeController.returnBike(station);
                NotificationDialog notificationDialog = new NotificationDialog("Trả xe thành công!");
                notificationDialog.show();
                System.out.println("Return bike successfully!");
                backHomeWhenSuccessfully(event);
            }
            else if (option.get() == ButtonType.CANCEL) {
                System.out.println("Chọn lại!");
            }
            else {
                System.out.println("else ------------------");
            }
        }
    }

    /**
     *
     */
    private void backHomeWhenSuccessfully(MouseEvent event) throws IOException {
        returnBikeHandler.getPreviousScreen().show();
    }
}
