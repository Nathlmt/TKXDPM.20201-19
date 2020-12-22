package org.tkxdpm20201.Nhom19.presentation.dialog;

import javafx.scene.control.Alert;

public class NotificationDialog extends BaseDialog {
    public NotificationDialog(String info) {
        super();
        this.dialog = new Alert(Alert.AlertType.INFORMATION);
        this.dialog.setContentText(info);
    }
    ;
    public void show() {
        this.dialog.show();
    }


}
