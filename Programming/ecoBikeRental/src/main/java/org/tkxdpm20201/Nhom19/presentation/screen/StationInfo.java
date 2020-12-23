package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import static org.tkxdpm20201.Nhom19.utils.Constants.STATION_INFO_PATH;

import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class StationInfo implements Initializable {

    private static final BaseScreenHandler stationHandler = new BaseScreenHandler(STATION_INFO_PATH);
    @FXML
    private ListView<Bike> bikeListView;
    @FXML
    Label address, availableBike, availableRack, acreage, stationName;
    private Station station;

    public StationInfo() {
    }

    public static BaseScreenHandler getStationHandler() {
        return stationHandler;
    }

//    public StationInfo(ObservableList<Bike> bikeList) {
//        this.bikeList = FXCollections.observableArrayList();
//    }
    //TODO: handle click event from stationListScreen to direct to StationInfoScreen
    private void displayStationInfo() {
         this.station = (Station) stationHandler.getEntityData();
         stationName.setText(this.station.getStationName());
         address.setText(this.station.getAddress());
         availableBike.setText(this.station.getAvailableBike().toString() + " xe");
         availableRack.setText(this.station.getAvailableRack().toString() + " chỗ");
         acreage.setText(this.station.getAcreage() + " m2");
    };

    public void back() {
        stationHandler.getPreviousScreen().show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStationInfo();
    }
}
