package org.tkxdpm20201.Nhom19.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentForm implements Initializable {

    @FXML
    private Label depositLabel;

    @FXML
    private Label additionalFeeLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardHoldNameField;

    @FXML
    private TextField expirationDateField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField issuingBankField;


    @FXML
    public void showPaymentForm(){


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitPaymentInfo(){

    }

//    public void displayPaymentForm(PayingInfo payingInfo){
//        depositLabel.setText(payingInfo.getDeposit().toString());
//        rentingFeeLabel.setText(payingInfo.getRentingFee().toString());
//        additionalFeeLabel.setText(payingInfo.getAdditionalFee() != null ? payingInfo.getAdditionalFee().toString() : "0");
//        transferAmountLabel.setText(payingInfo.getAmount().toString());
//    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/popup_list_station_return.fxml"));
        Parent popup = loader.load();
        Scene scene = new Scene(popup);
        stage.setScene(scene);
    }


}
