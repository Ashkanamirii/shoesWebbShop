package controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modell.bl.OrderLineItemManagerImpl;
import modell.to.Shoes;
import utils.Invoice;
import utils.UserLogin;

import javax.security.auth.callback.Callback;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hodei Eceiza
 * Date: 2/20/2021
 * Time: 22:50
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ConfirmShopping {
    public TableView orderTable;
    @FXML
    public TableColumn confirmQuantity;

    @FXML
    public TableColumn confirmBrand;

    @FXML
    public TableColumn confirmPrice;

    @FXML
    public Label totalPrice;

    @FXML
    public Label deliveryCosts;

    @FXML
    public Button confirmB;

    @FXML
    public Button cancelB;

    @FXML
    public Label customerName;

    @FXML
    public Label customerAddress;

    @FXML
    public Label customerCountry;
    public Label orderNr;

    private ObservableList<Shoes> shoesData;
    private final static int DELIVERYCOST=225;
    private OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    public void initialize(){
        System.out.println(shoesData);
        confirmBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        confirmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        confirmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        orderTable.setItems(shoesData);
        customerName.setText(UserLogin.getCustomer().getName());
        customerAddress.setText(UserLogin.getCustomer().getAddress());
        customerCountry.setText(UserLogin.getCustomer().getCountry());
        deliveryCosts.setText(DELIVERYCOST+"");

        //totalPrice.setText(shoesData.stream().map(s -> s.getPrice() * s.getQuantity()).reduce(0, Integer::sum).toString());
    }


    public void setData(ObservableList<Shoes> shoesData,int orderId){
        this.shoesData=shoesData;
        orderNr.setText(orderId+"");
        initialize();
        totalPrice.setText(shoesData.stream().map(s -> s.getPrice() * s.getQuantity()).reduce(0.0, (f,s)->f+s) + DELIVERYCOST +"");
       confirmB.setOnAction(e->{
               shoesData.forEach(s-> {
                   try {
                       orderManager.getAddTOCart(UserLogin.getCustomer().getId(),orderId,s.getId(),s.getQuantity(),1);
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               });
           Alert a=new Alert(Alert.AlertType.INFORMATION);

               a.setContentText("Your order with id: " + orderId +
                       " is on the way\n"+
                       "here a small resume\n" +
                       invoiceFormatted(orderId) + "\nYou can go to your page and UPDATE your order if you want"); //TODO:Show the invoice

           a.setHeaderText("SHOPPING CONFIRMED!");
           a.setHeight(250);
           a.showAndWait();
                closeStage(e);

       });
        cancelB.setOnAction(e->{
            shoesData.forEach(s-> {
                try {
                    orderManager.getAddTOCart(UserLogin.getCustomer().getId(),orderId,s.getId(),s.getQuantity(),3);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            closeStage(e);
        });
        // shoesData.forEach(System.out::println);
    }

    private void closeStage(Event e) {
        Node source = (Node)  e.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();


    }
    private String invoiceFormatted(int orderId){
        List<Invoice> invoiceS = null;
        try {

            invoiceS= orderManager.getInvoice(orderId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      return  invoiceS.stream().map(Invoice::toString).collect(Collectors.joining("\n"));
    }

}
