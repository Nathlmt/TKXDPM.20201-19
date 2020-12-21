package org.tkxdpm20201.Nhom19.presentation.dialog;

import javafx.scene.control.Alert;

public class NotificationDialog {
    private final Alert notification;

    public NotificationDialog(String info) {
        this.notification = new Alert(Alert.AlertType.INFORMATION);
        this.notification.setContentText(info);
    }
    ;
    public void show() {
        notification.show();
    }


}
