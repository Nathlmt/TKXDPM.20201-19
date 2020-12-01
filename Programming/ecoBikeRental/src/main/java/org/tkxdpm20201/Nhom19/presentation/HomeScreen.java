package org.tkxdpm20201.Nhom19.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreen implements Initializable {

    @FXML
    private Label rentedTime;

    @FXML
    private Label fee;

    @FXML
    private Label status;

    @FXML
    private ImageView bikeImageView;

    @FXML
    private Pane rentingBikeInfoPane;

    @FXML
    private Hyperlink findStation;

    @FXML
    private Hyperlink returnBike;

    public HomeScreen(){

    }

    public void handleButtonClicks(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == findStation) {
            loadStage(mouseEvent, "/fxml/station_list_screen.fxml");
        } else if (mouseEvent.getSource() == returnBike) {
            loadStage(mouseEvent, "/fxml/popup_list_station_return.fxml");
        }
    }

    private void loadStage(ActionEvent event, String fxml) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));
            Parent viewListStation = loader.load();
            Scene scene = new Scene(viewListStation);
            App.setSizeForWindow(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void returnBikeSuccessfully(){
        rentingBikeInfoPane.setDisable(true);
        rentingBikeInfoPane.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
