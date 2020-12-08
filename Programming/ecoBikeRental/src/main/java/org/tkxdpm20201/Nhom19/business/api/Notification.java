package org.tkxdpm20201.Nhom19.business.api;

public class Notification {
    private boolean status;
    private String message;

    public Notification(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
