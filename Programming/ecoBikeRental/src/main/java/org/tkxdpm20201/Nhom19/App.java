package org.tkxdpm20201.Nhom19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/home_screen.fxml"));
        StackPane layout = new StackPane();
        Button button = new Button();
        button.setText("ok ");
        layout.getChildren().add(button);
//        System.out.println("ok nha");
        stage.setScene(new Scene(root, 300, 300));
        stage.setTitle("Hello world!");
        stage.show();
    }





}