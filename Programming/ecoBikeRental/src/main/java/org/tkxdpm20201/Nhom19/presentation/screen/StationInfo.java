package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import static org.tkxdpm20201.Nhom19.utils.Constants.STATION_INFO_PATH;

import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class StationInfo implements Initializable {

//    private final ObservableList<Bike> bikeList;
    private static final BaseScreenHandler stationHandler = new BaseScreenHandler(STATION_INFO_PATH);
    @FXML
    private ListView<Bike> bikeListView;
    public static BaseScreenHandler getStationHandler() {
        return stationHandler;
    }

//    public StationInfo(ObservableList<Bike> bikeList) {
//        this.bikeList = FXCollections.observableArrayList();
//    }
    //TODO: handle click event from stationListScreen to direct to StationInfoScreen
    public void back() {
        stationHandler.getPreviousScreen().show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
