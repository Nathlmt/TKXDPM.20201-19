package org.tkxdpm20201.Nhom19.presentation.Dialog;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ErrorDialog {
    private final Alert errorDialog;

    public ErrorDialog(String info) {
        this.errorDialog = new Alert(AlertType.ERROR);
        this.errorDialog.setContentText(info);
    }

    ;

    public void show() {
        errorDialog.show();
    }

    public Alert getErrorDialog() {
        return errorDialog;
    }
}

