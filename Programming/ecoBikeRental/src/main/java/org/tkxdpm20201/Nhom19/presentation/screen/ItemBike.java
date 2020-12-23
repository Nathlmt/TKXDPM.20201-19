package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.io.IOException;

public class ItemBike extends ListCell<Bike> {


    @FXML
    private Label nameLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label pinLabel;

    @FXML
    private FlowPane flowPane;

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

            typeLabel.setText(bike.getType());
            nameLabel.setText(bike.getName());
//            if(bike.get)
            pinLabel.setText(null);
            setText(null);
            setGraphic(flowPane);
        }
    }
}
