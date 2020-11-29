package org.tkxdpm20201.Nhom19.presentation;

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
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StationListScreen implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;


    public StationListScreen(){
        stationObservableList = FXCollections.observableArrayList();
    }

    public void returnHome(ActionEvent event) throws IOException {
        System.out.println("ok home");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        Parent home = loader.load();
        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStationListView());
    }
}
