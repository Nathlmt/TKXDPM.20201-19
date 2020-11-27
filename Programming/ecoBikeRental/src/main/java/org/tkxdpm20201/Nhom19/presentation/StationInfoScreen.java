package org.tkxdpm20201.Nhom19.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.tkxdpm20201.Nhom19.persistence.entities.Bike;

import java.net.URL;
import java.util.ResourceBundle;

public class StationInfoScreen implements Initializable {

    private final ObservableList<Bike> bikeList;

    @FXML
    private ListView<Bike> bikeListView;

    public StationInfoScreen(ObservableList<Bike> bikeList) {
        this.bikeList = FXCollections.observableArrayList();
    }



    // TODO: handle click event from stationListScreen to direct to StationInfoScreen

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bikeList.addAll(new Bike(33333, "Xe Dap", 40),
                    new Bike(12344, "Xe Dap", 40));
        bikeListView.setItems(bikeList);
        bikeListView.setCellFactory(bikeListView1 -> new ItemBikeListView());

    }
}
