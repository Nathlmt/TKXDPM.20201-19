package org.tkxdpm20201.Nhom19.presentation.dialog;

import javafx.scene.control.Alert;
/**
 * Class use for notification Info Dialog
 * @author LeMinhTuan
 */
public class NotificationDialog extends BaseDialog {
    /**
     *
     * @param info: Error describe
     */
    public NotificationDialog(String info) {
        super();
        this.dialog = new Alert(Alert.AlertType.INFORMATION);
        this.dialog.setContentText(info);
    }
    ;

    /**
     * show dialog
     */
    public void show() {
        this.dialog.show();
    }


}
