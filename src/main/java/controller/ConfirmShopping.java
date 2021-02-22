package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modell.bl.OrderLineItemManagerImpl;
import modell.to.Shoes;
import utils.UserLogin;

import java.sql.SQLException;

/**
 * Created by Hodei Eceiza
 * Date: 2/20/2021
 * Time: 22:50
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ConfirmShopping {
    @FXML
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
    @FXML
    public Label orderNr;

    private OrderLineItemManagerImpl orderLineItemManager = new OrderLineItemManagerImpl();
    private ObservableList<Shoes> shoesData;
    private final static int DELIVERYCOST = 225;

    public void initialize() {
        System.out.println(shoesData);
        confirmBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        confirmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        confirmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        orderTable.setItems(shoesData);
        customerName.setText(UserLogin.getCustomer().getName());
        customerAddress.setText(UserLogin.getCustomer().getAddress());
        customerCountry.setText(UserLogin.getCustomer().getCountry());
        deliveryCosts.setText(DELIVERYCOST + "");

        totalPrice.setText(shoesData.stream().map(s ->
                s.getPrice() * s.getQuantity()).reduce(0.00, Double::sum).toString());
    }


    public void setData(ObservableList<Shoes> shoesData, int orderId) {
        this.shoesData = shoesData;
        orderNr.setText(orderId + "");
        initialize();
        totalPrice.setText(shoesData.stream().map(s ->
                s.getPrice() * s.getQuantity()).reduce(0.0, (f, s) -> f + s) + DELIVERYCOST + "");
        confirmB.setOnAction(e -> {
            shoesData.forEach(s -> {
                try {
                    orderLineItemManager.getAddTOCart(
                            UserLogin.getCustomer().getId(), orderId, s.getId(), s.getQuantity(), 1);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            });
            closeStage(e);
        });
        cancelB.setOnAction(e -> {
            shoesData.forEach(s -> {
                try {
                    orderLineItemManager.getAddTOCart(
                            UserLogin.getCustomer().getId(), orderId, s.getId(), s.getQuantity(), 3);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            });
            closeStage(e);
        });
        shoesData.forEach(System.out::println);
    }

    private void closeStage(Event e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
