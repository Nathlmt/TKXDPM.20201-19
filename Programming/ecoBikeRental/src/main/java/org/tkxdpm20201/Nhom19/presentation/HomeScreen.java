package org.tkxdpm20201.Nhom19.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen {

    @FXML
    private Hyperlink returnBikeHyp;

    public void returnBikeHyp(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/station_list_screen.fxml"));
        Parent viewListStation = loader.load();
        Scene scene = new Scene(viewListStation);
        stage.setScene(scene);
        stage.show();

    }

    public void requestRentBike(){

    }

    public void displaySuccessTransaction(){

    }

    public void showRentingBikeInfo(){

    }

}
