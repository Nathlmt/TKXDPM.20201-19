package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.tkxdpm20201.Nhom19.business.controller.FindStationController;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import static org.tkxdpm20201.Nhom19.utils.Constants.STATION_INFO_PATH;

import org.tkxdpm20201.Nhom19.data.entities.station.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import org.tkxdpm20201.Nhom19.presentation.dialog.NotificationDialog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StationInfo implements Initializable {
    private final ObservableList<Bike> bikeObservableList;
    private final FindStationController findStationController;
    private static final BaseScreenHandler stationHandler = new BaseScreenHandler(STATION_INFO_PATH);
    @FXML
    private ListView<Bike> bikeListView;
    @FXML
    Label address, availableBike, availableRack, acreage, stationName;
    private Station station;

    public StationInfo() {
            this.bikeObservableList = FXCollections.observableArrayList();
            this.findStationController = new FindStationController();
    }

    public static BaseScreenHandler getStationHandler() {
        return stationHandler;
    }

    private int showBikeList(int stationId) throws SQLException {
        List<Bike> listBike = this.findStationController.getBikeStation(stationId);
        this.bikeObservableList.addAll(listBike);
        bikeListView.setItems(bikeObservableList);
        bikeListView.setCellFactory(bikeListView -> new ItemBike());
        return listBike.size();
    };

    private void displayStationInfo() {
        try {
            this.station = (Station) stationHandler.getEntityData();
            stationName.setText(this.station.getStationName());
            address.setText(this.station.getAddress());
//            availableBike.setText(this.station.getAvailableBike().toString() + " xe");
            availableRack.setText(this.station.getAvailableRack().toString() + " chỗ");
            acreage.setText(this.station.getAcreage() + " m2");
            int countBike = showBikeList(station.getId());
            availableBike.setText(countBike + " xe");
        } catch (Exception e) {

        }
    };

    public void back() {
        stationHandler.getPreviousScreen().show();
    }

    public void chooseBike(MouseEvent event) throws IOException {
        Bike bike = bikeListView.getSelectionModel().getSelectedItem();
        if(bike == null ){
            // DO NOTHING
            System.out.println("Bike click null");
        }
        BaseScreenHandler bikeInfoHandler = BikeInfo.getBikeInfoHandler();
        bikeInfoHandler.setEntityData(bike);
        bikeInfoHandler.setScreenStage(stationHandler.getScreenStage());
        bikeInfoHandler.setPreviousScreen(stationHandler);
        bikeInfoHandler.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStationInfo();
    }
}
