package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modell.bl.OrderLineItemManagerImpl;
import utils.Invoice;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;


public class MyPagesOrders {
    @FXML
    public AnchorPane myPagesOrdersPane;
    public Button logout;
    public Button goToShopping;
    public Button myPagesBtn;
    public Button surveyBtn;
    public Label loginL;
    //public Pane shoesDescription_returnsP;
    public Utils changeScene;
    public VBox surveyBtnBox;
    public Button searchBtn;
    public TableView invoiceTable;
    public TableColumn shoesNumberC;
    public TableColumn brandC;
    public TableColumn colorC;
    public TableColumn priceC;
    public TableColumn quantityC;
    private OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    private ObservableList<Invoice> invoice;
    public void initialize() {
        try {
            invoice= FXCollections.observableArrayList(orderManager.getInvoice(99));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));

        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));
        invoiceTable.setItems(invoice);









        changeScene = new Utils();

        loginL.setText("You login as " + UserLogin.getCustomer().getName() );

        logout.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", myPagesOrdersPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", myPagesOrdersPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        myPagesBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesHome.fxml", myPagesOrdersPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        surveyBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesSurvey.fxml", myPagesOrdersPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }
}


//Måste fixa fel Om vi har tid (On Action)
// On Action fungerade inte
// Jag testade att första view ska vara home.fxml
// Main class -->
// Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
// Om ma klicka t.ex LoginBtn kommer det massor med fel

/*
        public void goToLogin (ActionEvent e){
            try {
                changeScene.changeScene("/userProfile.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


        public void goToWS (ActionEvent e){
            try {
                changeScene.changeScene("/webbshopPage.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


        public void goToRegi (ActionEvent e)  {
            try {
                changeScene.changeScene("/XXXXX.fxml", mainPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

*/


