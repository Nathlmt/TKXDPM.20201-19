package org.tkxdpm20201.Nhom19.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tkxdpm20201.Nhom19.App;
import org.tkxdpm20201.Nhom19.data.entities.BaseEntity;

import java.io.IOException;

/**
 * screen handler use for share data from different screen,
 */
public class BaseScreenHandler {
    private final String screenPath;
    private Stage screenStage;
    private BaseScreenHandler previousScreen;
    private BaseEntity entityData;


    public BaseScreenHandler(String screenPath) {
        this.screenPath = screenPath;
    };

    public BaseScreenHandler getPreviousScreen() {
        return previousScreen;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(this.screenPath));
            Parent parentNodes = loader.load();
            Scene scene = new Scene(parentNodes);
            App.setSizeForWindow(screenStage);
            screenStage.setScene(scene);
            screenStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getScreenStage() {
        return this.screenStage;
    }

    public BaseEntity getEntityData() {
        return entityData;
    }

    public void setEntityData(BaseEntity entityData) {
        this.entityData = entityData;
    }

    public void setScreenStage(Stage screenStage) {
        this.screenStage = screenStage;
    }
    public void setPreviousScreen(BaseScreenHandler prevScreen) {
        this.previousScreen = prevScreen;
    }
}
