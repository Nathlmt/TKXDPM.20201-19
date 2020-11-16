package org.tkxdpm20201.Nhom19.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreen implements Initializable {

    @FXML
    private Circle circleProfile;

    @FXML
    private ImageView imageView;




    public void requestReturnBike(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
