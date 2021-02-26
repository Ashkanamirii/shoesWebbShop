package controller;


import enumation.Error;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bl.OrderLineItemManagerImpl;
import model.to.Shoes;
import utils.UserLogin;

import java.io.IOException;
import java.sql.SQLException;

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
    public AnchorPane mainPage;
    private ObservableList<Shoes> shoesData;
    private final static int DELIVERYCOST = 225;
    private OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();

    public void initialize() {

        confirmBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        confirmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        confirmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        orderTable.setItems(shoesData);
        customerName.setText(UserLogin.getCustomer().getName());
        customerAddress.setText(UserLogin.getCustomer().getAddress());
        customerCountry.setText(UserLogin.getCustomer().getCountry());
        deliveryCosts.setText(DELIVERYCOST+"");

    }
    public void showAlert(){
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("TIMED OUT");
        a.setContentText("TIMED OUT,your order has been cancelled due to your slow response.");
        a.show();
    }

    public void setData(ObservableList<Shoes> shoesData,int orderId){
        // Det här för auto_cancel
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(30),
                        new KeyValue(confirmB.visibleProperty(), false)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(30),
                new KeyValue(cancelB.visibleProperty(), false)));

        timeline.setOnFinished(e->showAlert());

        timeline.play();


        this.shoesData=shoesData;
        orderNr.setText(orderId+"");
        initialize();
        totalPrice.setText(shoesData.stream().map(
                s -> s.getPrice() * s.getQuantity()).reduce(0.0, Double::sum) + DELIVERYCOST + "");
        confirmB.setOnAction(e -> {

            shoesData.forEach(s -> {
                try {
                    orderManager.getAddTOCart(
                            UserLogin.getCustomer().getId(), orderId, s.getId(), s.getQuantity(), 1);
                } catch (SQLException | IOException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                    System.out.println(Error.DATABASE);
                    System.out.println(Error.CONNECTION);
                }
            });


                closeStage(e);

        });
        cancelB.setOnAction(e -> {
            shoesData.forEach(s -> {
                try {
                    orderManager.getAddTOCart(UserLogin.getCustomer().getId(), orderId, s.getId(),
                            s.getQuantity(), 3);
                } catch (SQLException | IOException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                    System.out.println(Error.DATABASE);
                    System.out.println(Error.CONNECTION);
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
}
