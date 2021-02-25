package controller;


import enumation.Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.bl.OrderLineItemManagerImpl;
import model.bl.OrderManagerImpl;
import model.to.Invoice;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;


public class MyPagesSurvey {
    @FXML
    public AnchorPane myPagesSurveyPane;
    public Button logout;
    public Button goToShopping;
    public TableView<Invoice> invoiceTable;
    public TableColumn<Invoice, String> orderDateC;
    public TableColumn<Invoice, String> brandC;
    public TableColumn<Invoice, String> colorC;
    public TableColumn<Invoice, Integer> shoesNumberC;
    public TableColumn<Invoice, Integer> quantityC;
    public TableColumn<Invoice, Double> priceC;
    public TableColumn<Invoice, Integer> totalPriceC;
    public Label idNumber;
    public Pane shoesDescription_surveyP;
    private ObservableList<Invoice> invoice;
    private final OrderLineItemManagerImpl orderLineItemManager = new OrderLineItemManagerImpl();
    private OrderManagerImpl orderManager = new OrderManagerImpl();
    public Utils changeScene;

    public void initialize() {

        try {
            invoice = FXCollections.observableArrayList(orderLineItemManager.getInvoice(orderManager.getLastOrderId(
                    UserLogin.getCustomer().getId()))); //have to fix
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println(Error.DATABASE);
            System.out.println(Error.CONNECTION);
        }
        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        totalPriceC.setCellValueFactory(new PropertyValueFactory("total_price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));
        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));
        orderDateC.setCellValueFactory(new PropertyValueFactory("orderDate"));
        invoiceTable.setItems(invoice);
        //show description
        invoiceTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
                loadShoesDesc(invoiceTable.getSelectionModel().getSelectedItem().getShoesId());
        });

        changeScene = new Utils();
        logout.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    private void loadShoesDesc(int shoesData) {
        shoesDescription_surveyP.getChildren().clear();
        Pane newLoadedPane = null;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/shoesDescription_Survey.fxml"));
            newLoadedPane = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ShoesDescriptionSurvey controller = loader.getController();
        controller.setData(shoesData);
        shoesDescription_surveyP.getChildren().add(newLoadedPane);
    }
}





