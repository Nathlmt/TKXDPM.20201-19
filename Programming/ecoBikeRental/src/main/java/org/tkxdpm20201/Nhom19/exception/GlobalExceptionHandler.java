package org.tkxdpm20201.Nhom19.exception;

import javafx.scene.control.Alert;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Server lỗi.. xin vui lòng thử lại sau!!!");
        alert.setTitle("ERROR");
    }
}
