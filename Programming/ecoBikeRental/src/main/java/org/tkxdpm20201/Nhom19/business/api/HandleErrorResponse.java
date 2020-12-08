package org.tkxdpm20201.Nhom19.business.api;

public class HandleErrorResponse {

    public static Notification handle(String errorCode){
        switch (errorCode){
            case ErrorCode.INTERNAL_SERVER_ERROR:
                return new Notification(false, "server api system error!");
            case ErrorCode.INVALID_AMOUNT:
                return new Notification(false, "amount is invalid");
            case ErrorCode.INVALID_CARD:
                return new Notification(false, "Card is invalid");
            case ErrorCode.NOT_ENOUGH_BALANCE:
                return new Notification(false, "not enough balance");
            case ErrorCode.NOT_ENOUGH_INFO:
                return new Notification(false, "not enough information");
            case ErrorCode.SUSPECTED_FRAUD:
                return new Notification(false, "suspected fraud!");
            case ErrorCode.VACANT_VERSION:
                return new Notification(false, "vacant version");
        }
        return new Notification(true, "successful transaction");
    }
}
