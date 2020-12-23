package org.tkxdpm20201.Nhom19.presentation.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.exception.PaymentException;
import org.tkxdpm20201.Nhom19.presentation.BaseScreenHandler;

import org.tkxdpm20201.Nhom19.business.controller.RentBikeController;
import org.tkxdpm20201.Nhom19.presentation.dialog.ErrorDialog;
import org.tkxdpm20201.Nhom19.presentation.dialog.NotificationDialog;
import org.tkxdpm20201.Nhom19.utils.Constants;
import org.tkxdpm20201.Nhom19.utils.RoundingDeposit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentForm implements Initializable {
    private final static BaseScreenHandler paymentFormHandler = new BaseScreenHandler(Constants.PAYMENT_INFO_PATH);
    @FXML
    private Label depositLabel;
    @FXML
    private Label rentingFeeLabel;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cardHoldNameField;
    @FXML
    private TextField expirationMonthField;
    @FXML
    private TextField expirationYearField;
    @FXML
    private TextField cvvField;
    @FXML
    private TextArea transactionContent;
    @FXML
    public void showPaymentForm(){
        Bike bike = (Bike) paymentFormHandler.getEntityData();
        depositLabel.setText(RoundingDeposit.up(bike.getPrice()) + " đ");
//        rentingFeeLabel.setText();
    }

    public static BaseScreenHandler getPaymentFormHandler() {
        return paymentFormHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPaymentForm();
    }

    public void submitPayment()  {
        RentBikeController rentBikeController = new RentBikeController();
        Card card = new Card(
                cardNumberField.getText() ,
                cardHoldNameField.getText(),
                cvvField.getText(),
                expirationMonthField.getText() + expirationYearField.getText()
        );
        if (rentBikeController.validateCartInfo(card)) {
            Bike bike = (Bike) paymentFormHandler.getEntityData();
            try {
                rentBikeController.handleRentBike(bike,
                        1,
                        bike.getPresentStation(),
                        bike.getPrice(),card,transactionContent.getText()
                );
                NotificationDialog notificationDialog = new NotificationDialog("Thuê xe thành công!");
                notificationDialog.show();
                //gp tp h
                Home.getHomeScreenHandler().show();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PaymentException paymentException) {
                ErrorDialog errorDialog =  new ErrorDialog(paymentException.getMessage());
                errorDialog.show();
            }
        };
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        paymentFormHandler.getPreviousScreen().show();
    }


}