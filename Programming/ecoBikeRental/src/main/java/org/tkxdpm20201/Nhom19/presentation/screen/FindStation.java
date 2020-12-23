package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.tkxdpm20201.Nhom19.business.controller.FindStationController;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import static org.tkxdpm20201.Nhom19.utils.Constants.FIND_STATION_PATH;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FindStation implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;
    private final static BaseScreenHandler findStationHandler = new BaseScreenHandler(FIND_STATION_PATH);

    public FindStation() {
        stationObservableList = FXCollections.observableArrayList();
    }

    private final FindStationController findStationController = new FindStationController();

    public static BaseScreenHandler getFindStationHandler() {
        return findStationHandler;
    }

    public void returnHome() {
        findStationHandler.getPreviousScreen().show();
    }

    private void showStationList() throws SQLException {
        List<Station> stationList = findStationController.getStationList();
        this.stationObservableList.addAll(stationList);
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStation());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showStationList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void chooseStation(MouseEvent mouseEvent) {

        Station station = listStation.getSelectionModel().getSelectedItem();
        if (station == null) return;
        BaseScreenHandler stationInfoHandler = StationInfo.getStationHandler();
        stationInfoHandler.setEntityData(station);
        stationInfoHandler.setScreenStage(findStationHandler.getScreenStage());
        stationInfoHandler.setPreviousScreen(findStationHandler);
        stationInfoHandler.show();
    }
}
