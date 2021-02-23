package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
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
    public TableView<Invoice> invoiceTable;
    public TableColumn<Invoice, Integer> shoesNumberC;
    public TableColumn<Invoice, String> brandC;
    public TableColumn<Invoice, String> colorC;
    public TableColumn<Invoice, Double> priceC;
    public TableColumn<Invoice, Integer> quantityC;
    private OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    private ObservableList<Invoice> invoice;

    public void initialize() {
        //get the order


        //get the invoice
        try {
            invoice = FXCollections.observableArrayList(orderManager.getInvoice(99));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        invoiceTable.setEditable(true);


        invoiceTable.setOnKeyPressed(e -> {
            TablePosition focus = invoiceTable.focusModelProperty().get().focusedCellProperty().get();
            invoiceTable.edit(focus.getRow(), focus.getTableColumn());
            System.out.println(invoice.toString());
        });


        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));

        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));

        invoiceTable.setItems(invoice);

        quantityC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        invoiceTable.getSelectionModel().cellSelectionEnabledProperty().set(true);


        quantityC.setOnEditCommit(e -> {
            if(e.getNewValue()>e.getOldValue()){
                Alert dialog=new Alert(Alert.AlertType.WARNING);
                dialog.setContentText("you can't buy more shoes with this order\nBut you can always go back to the shop and buy more :)");
                dialog.showAndWait();
            }
            final Integer value = e.getNewValue() != null ? e.getNewValue() : e.getOldValue();
            (e.getTableView().getItems().get(e.getTablePosition().getRow())).setQuantity(value);

            invoiceTable.refresh();

            //for testing
            System.out.println(invoice.toString());
        });


        changeScene = new Utils();

        loginL.setText("You login as " + UserLogin.getCustomer().getName());

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



