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
import modell.bl.CustomerManagerImpl;
import modell.bl.OrderLineItemManagerImpl;
import utils.History;
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
    private final OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    private final CustomerManagerImpl customerManager =new CustomerManagerImpl();
    public TableView<History> ordersTable;
    public TableColumn<History,Integer> orderId;
    public TableColumn <History,String>orderDate;
    public Button updateOrderLine;
    public Label orderIdLabel;
    private ObservableList<Invoice> invoice;
    private ObservableList<History> userHistory;

    public void initialize() {

        //TODO: implement searching (there is already a method in webshoppage)
        //get the order

        try {
           userHistory=FXCollections.observableArrayList(customerManager.customerHistory(UserLogin.getCustomer().getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//set the orders
        ordersTable.setItems(userHistory);

        System.out.println(UserLogin.getCustomer().getId());
        //set the orders
        orderId.setCellValueFactory(new PropertyValueFactory("orderId"));
        orderDate.setCellValueFactory(new PropertyValueFactory("orderDate"));

        //on order select show invoice
        ordersTable.setOnMouseClicked(e->{
            if(e.getClickCount()==2) {
                int orderId=ordersTable.getSelectionModel().getSelectedItem().getOrderId();

                orderIdLabel.setText(orderId+"");
                try {
                    invoice = FXCollections.observableArrayList(orderManager.getInvoice
                            (orderId));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                invoiceTable.setItems(invoice);
                invoiceTable.refresh();
            }
        });



        //get the invoice
        try {
            if(userHistory.size()>=0){
            invoice = FXCollections.observableArrayList(orderManager.getInvoice(userHistory.get(0).getOrderId()));}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //set the invoice
        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));

        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));

        invoiceTable.setItems(invoice);
        invoiceTable.setEditable(true);
//edit the invoice

        invoiceTable.setOnKeyPressed(e -> {
            TablePosition focus = invoiceTable.focusModelProperty().get().focusedCellProperty().get();
            invoiceTable.edit(focus.getRow(), focus.getTableColumn());
            System.out.println(invoice.toString());
        });

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
        });


        //send to be returned
        updateOrderLine.setOnAction(e->{
            invoice.forEach(i->{
                if(i.quantityToReturn()!=0){
                try {
                orderManager.getAddTOCart(
                        UserLogin.getCustomer().getId(), i.getOrderId(), i.getShoesId(),i.quantityToReturn(),5);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }}});

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



