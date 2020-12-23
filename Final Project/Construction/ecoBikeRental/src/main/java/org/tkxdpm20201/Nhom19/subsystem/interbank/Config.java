package org.tkxdpm20201.Nhom19.subsystem.interbank;

public class Config {

    public static final String BASE_URL = "https://ecopark-system-api.herokuapp.com";
    public static final String API_VERSION = "1.0.1";
    public static final String PAY = "pay";
    public static final String REFUND = "refund";
    public static final String PROCESS_TRANSACTION_URL = BASE_URL + "/api/card/processTransaction";

    public static final String APP_CODE = "BuIB+YIuIsQ=";
    public static final String SECRET_KEY = "BBxaiI0BzoA=";


    public static final String CARD_CODE = "118609_group19_2020";
    public static final String CVV = "907";
    public static final String OWNER = "Group 19";
    public static final String DATE_EXPIRED = "1125";

    public final static String SUCCESS_TRANSACTION = "00";
    public final static String INVALID_CARD = "01";
    public final static String NOT_ENOUGH_BALANCE = "02";
    public final static String INTERNAL_SERVER_ERROR = "03";
    public final static String SUSPECTED_FRAUD = "04";
    public final static String NOT_ENOUGH_INFO = "05";
    public final static String VACANT_VERSION = "06";
    public final static String INVALID_AMOUNT = "07";
}
