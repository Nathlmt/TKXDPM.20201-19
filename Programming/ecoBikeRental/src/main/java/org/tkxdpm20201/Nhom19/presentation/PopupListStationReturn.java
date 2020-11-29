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
import org.tkxdpm20201.Nhom19.business.controller.ReturnBikeController;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupListStationReturn implements Initializable {

    @FXML
    private ListView<Station> listStation;

    private final ObservableList<Station> stationObservableList;
    private final ReturnBikeController returnBikeController;

    public PopupListStationReturn(){
        stationObservableList = FXCollections.observableArrayList();
        returnBikeController = new ReturnBikeController();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showStationList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void showStationList() throws SQLException {

        List<Station> stationList = returnBikeController.getStationList();
        this.stationObservableList.addAll(stationList);
        listStation.setItems(stationObservableList);
        listStation.setCellFactory(stationListView -> new ItemStationListView());

    }

    @FXML
    public void returnHome(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/home_screen.fxml"));
        Parent home = loader.load();
        Scene scene = new Scene(home);
        stage.setScene(scene);

    }

    @FXML
    public void chooseStationToReturn(MouseEvent event) throws IOException {
        Station station = listStation.getSelectionModel().getSelectedItem();
        if(station == null ){
            // DO NOTHING
            System.out.println("station click null");
        }
        else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to return this Bike right now at "+ station.getStationName()+" station?");
            alert.setTitle("Confirm");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                boolean result = returnBikeController.returnBike(station, null);
                if(result){
                    backHomeWhenSuccessfully(event);
                    System.out.println("OKKKKK successfully!");
                }
                else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setContentText("return Bike failed... please try again!");
                    alert.setTitle("Error");
                    alert.showAndWait();
                }
            }
            else if (option.get() == ButtonType.CANCEL) {
                System.out.println("cho chon lai");
            }
            else {
                System.out.println("else ------------------");
            }
        }
    }

    /**
     *
     */
    private void backHomeWhenSuccessfully(MouseEvent event) throws IOException {
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/home_screen.fxml"));
        Parent home = loader.load();
        Scene scene = new Scene(home);
        HomeScreen homeScreenController = loader.getController();
        homeScreenController.returnBikeSuccessfully();
        stage.setScene(scene);

    }


    // TODO : hàm này chỉ dành cho khi rent bike___ bị nhầm chỗ này>> vì return bike thì không cần phải hiện paymentForm nữa
//    private void directToPaymentForm(PayingInfo payingInfo, MouseEvent event) throws IOException {
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/payment_form.fxml"));
//        Parent popupListStation = loader.load();
//        Scene scene = new Scene(popupListStation);
//        PaymentForm paymentFormController = loader.getController();
//        paymentFormController.displayPaymentForm(payingInfo);
//        stage.setScene(scene);
//
//    }
}
