package org.tkxdpm20201.Nhom19.presentation.dialog;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

/**
 * Class use for notification Error Dialog
 * @author LeMinhTuan
 */
public class ErrorDialog extends BaseDialog{
    /**
     *
     * @param info: Error describe
     */
    public ErrorDialog(String info) {
        super();
        this.dialog = new Alert(AlertType.ERROR);
        this.dialog.setContentText(info);
    };
}

