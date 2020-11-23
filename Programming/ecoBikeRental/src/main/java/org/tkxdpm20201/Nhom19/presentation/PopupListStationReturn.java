package org.tkxdpm20201.Nhom19.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupListStationReturn implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;

    public PopupListStationReturn(){
        stationObservableList = FXCollections.observableArrayList();
        stationObservableList.addAll(
                new Station("Hanoi", "quoc oai, hanoi", "12 m2", 2, 4, "dang hoat dong", new Date()),
                new Station("Hanoi2", "quoc oai2, hanoi", "32 m2", 2, 4, "dang hoat dong", new Date())
        );
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStationListView());
    }

    @FXML
    public void returnHome(MouseEvent event) throws IOException {
        System.out.println("ok home");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home_screen.fxml"));
        Parent home = loader.load();
        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void selectItem(MouseEvent event){
        Station station = listStation.getSelectionModel().getSelectedItem();
        if(station == null ){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Nothing");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to return this Bike rightnow at "+ station.getStationName()+" station?");
            alert.setTitle("Confirm");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.out.println("chuyen sang trang payment form  ======================================= TODO    ");
            } else if (option.get() == ButtonType.CANCEL) {
                System.out.println("cho chon lai");
            } else {
                System.out.println("else ------------------");
            }
        }
    }
}
