package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 660;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home_screen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        setSizeForWindow(stage);
        stage.setResizable(false);
        stage.show();
    }

    public static void setSizeForWindow(Stage stage){
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }


}