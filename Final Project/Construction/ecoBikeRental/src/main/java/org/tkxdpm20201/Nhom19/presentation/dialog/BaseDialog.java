package org.tkxdpm20201.Nhom19.presentation.dialog;

import javafx.scene.control.Alert;

public abstract class BaseDialog {
    protected Alert dialog;

    public void show() {
        dialog.show();
    }
}