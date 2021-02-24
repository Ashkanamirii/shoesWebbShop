package controller;


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
import javafx.scene.text.Text;
import modell.bl.OrderLineItemManagerImpl;
import modell.bl.ShoesManagerImpl;
import modell.to.Shoes;
import utils.Invoice;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MyPagesSurvey {
    @FXML
    public AnchorPane myPagesSurveyPane;
    public Button logout;
    public Button goToShopping;
    public Button myPagesBtn;
    public Button ordersBtn;
    public Label loginL;
    //public Pane shoesDescription_surveyP;
    public Utils changeScene;
    public TableView <Invoice>invoiceTable;
    public TableColumn<Invoice,String> orderDateC;
    public TableColumn <Invoice,String> brandC;
    public TableColumn <Invoice,String> colorC;
    public TableColumn <Invoice,Integer>shoesNumberC;
    public TableColumn <Invoice,Integer>quantityC;
    public TableColumn <Invoice,Double>priceC;
    public TableColumn <Invoice,Integer>totalPriceC;
    public Label idNumber;
    public Pane shoesDescription_surveyP;
    public VBox surveyBtnBox;
    public Button surveyBtn;
    private ObservableList<Invoice> invoice;
    private final OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    private final ShoesManagerImpl shoesManager=new ShoesManagerImpl();
    private List<Shoes> shoesList;

    public void initialize() {



        try {
            invoice = FXCollections.observableArrayList(orderManager.getInvoice(99)); //have to fix
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //set the invoice
        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        totalPriceC.setCellValueFactory(new PropertyValueFactory("total_price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));
        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));
        orderDateC.setCellValueFactory(new PropertyValueFactory("orderDate"));

    invoiceTable.setItems(invoice);

    //show description
    invoiceTable.setOnMouseClicked(e->{ if (e.getClickCount() == 2)
        loadShoesDesc( invoiceTable.getSelectionModel().getSelectedItem().getShoesId());});








        changeScene = new Utils();


        loginL.setText("You login as " + UserLogin.getCustomer().getName());

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

        myPagesBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesHome.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        ordersBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesOrders.fxml", myPagesSurveyPane);
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





