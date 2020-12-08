package org.tkxdpm20201.Nhom19.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import org.tkxdpm20201.Nhom19.data.entities.Bike;

import java.io.IOException;

public class ItemBikeListView extends ListCell<Bike> {

    @FXML
    private Label bikeIdLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label pinLabel;

    @FXML
    private HBox hBox;

    @Override
    public void updateItem(Bike bike, boolean empty){
        super.updateItem(bike, empty);
        if(empty || bike == null){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item_bike.fxml"));
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            bikeIdLabel.setText(bike.getId().toString());
            typeLabel.setText(bike.getType());
//            pinLabel.setText("Pin: " + bike.getBattery());
            setText(null);
            setGraphic(hBox);
        }
    }
}
