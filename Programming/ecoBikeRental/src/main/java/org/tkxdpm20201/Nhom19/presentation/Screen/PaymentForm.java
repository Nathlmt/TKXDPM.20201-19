package org.tkxdpm20201.Nhom19.presentation.Screen;

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
import org.tkxdpm20201.Nhom19.data.entities.Bike;
import org.tkxdpm20201.Nhom19.data.model.RentingBike;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;
import static org.tkxdpm20201.Nhom19.utils.Constants.PAYMENT_INFO_PATH;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentForm implements Initializable {
    private final static BaseScreenHandler paymentFormHandler = new BaseScreenHandler(PAYMENT_INFO_PATH);
    @FXML
    private Label depositLabel;
    @FXML
    private Label rentingFeeLabel;
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
        Bike bike = (Bike) paymentFormHandler.getEntityData();
        depositLabel.setText(bike.getPrice().toString() + " đ");
//        rentingFeeLabel.setText();
    }

    public static BaseScreenHandler getPaymentFormHandler() {
        return paymentFormHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPaymentForm();
    }

    public void submitPayment() throws SQLException {
        Bike bike = (Bike) paymentFormHandler.getEntityData();
        RentBikeController rentBikeController = new RentBikeController();
        System.out.println(bike.getId());
        System.out.println(bike.getPrice());
        rentBikeController.handleRentBike(bike.getId(),1,bike.getPresentStation());
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        paymentFormHandler.getPreviousScreen().show();
    }


}
