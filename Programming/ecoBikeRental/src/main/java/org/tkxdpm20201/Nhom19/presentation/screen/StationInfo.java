package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.tkxdpm20201.Nhom19.business.controller.FindStationController;
import org.tkxdpm20201.Nhom19.data.daos.BikeDao;
import org.tkxdpm20201.Nhom19.data.daos.implement.BikeDaoImp;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import static org.tkxdpm20201.Nhom19.utils.Constants.STATION_INFO_PATH;

import org.tkxdpm20201.Nhom19.data.entities.station.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StationInfo implements Initializable {
    private final ObservableList<Bike> bikeObservableList;
    private FindStationController findStationController;
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

    private void showBikeList(int stationId) throws SQLException {
        List<Bike> listBike = this.findStationController.getBikeStation(stationId);
        this.bikeObservableList.addAll(listBike);
        bikeListView.setItems(this.bikeObservableList);
        bikeListView.setCellFactory(bikeListView -> new ItemBike());
    };

    private void displayStationInfo() {
        try {
            this.station = (Station) stationHandler.getEntityData();
            stationName.setText(this.station.getStationName());
            address.setText(this.station.getAddress());
            availableBike.setText(this.station.getAvailableBike().toString() + " xe");
            availableRack.setText(this.station.getAvailableRack().toString() + " chỗ");
            acreage.setText(this.station.getAcreage() + " m2");
            showBikeList(station.getId());
        } catch (Exception e) {

        }
    };

    public void back() {
        stationHandler.getPreviousScreen().show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStationInfo();
    }
}
