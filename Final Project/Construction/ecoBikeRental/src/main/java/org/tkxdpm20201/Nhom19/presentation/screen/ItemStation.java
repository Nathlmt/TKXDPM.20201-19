package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;

import java.io.IOException;

public class ItemStation extends ListCell<Station> {

    @FXML
    private Label nameLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private FlowPane flowPane;

    @Override
    public void updateItem(Station station, boolean empty){
        super.updateItem(station, empty);
        if(empty || station == null){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item_station.fxml"));
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            nameLabel.setText(station.getStationName());
            infoLabel.setText(station.getAddress() + " - " + station.getAcreage());
            statusLabel.setText(station.getStatus());
            setText(null);
            setGraphic(flowPane);
        }
    }
}
