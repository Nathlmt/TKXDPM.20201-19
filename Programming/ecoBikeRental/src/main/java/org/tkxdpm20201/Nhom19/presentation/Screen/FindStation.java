package org.tkxdpm20201.Nhom19.presentation.Screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.data.entities.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import static org.tkxdpm20201.Nhom19.utils.Constants.FIND_STATION_PATH;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FindStation implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;
    private final static BaseScreenHandler findStationHandler = new BaseScreenHandler(FIND_STATION_PATH);

    public FindStation() {
        stationObservableList = FXCollections.observableArrayList();
    }

    public static BaseScreenHandler getFindStationHandler() {
        return findStationHandler;
    }

    public void returnHome(ActionEvent event) throws IOException {
        findStationHandler.getPreviousScreen().show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStation());
    }
}
