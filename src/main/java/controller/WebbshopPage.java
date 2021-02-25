package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.bl.BrandManagerImpl;
import model.bl.CategoryManagerImpl;
import model.bl.OrderLineItemManagerImpl;
import model.bl.ShoesManagerImpl;
import model.to.Brand;
import model.to.Category;
import model.to.Shoes;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebbshopPage {
    @FXML
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;
    public TableColumn c4;
    public TableColumn c5;
    public TableColumn c6;
    public TableColumn c7;
    public ComboBox showColors;
    public Button showTable;
    public TextField searchField;
    public Button startLogInB;
    public Label loginL;
    public PasswordField passF;
    public TextField email;
    public TableView shoppingCartView;
    public TableColumn cartPrice;
    public TableColumn cartBrand;
    public TableColumn cartQuantity;
    public Label totalPriceL;
    public TableView shoesTable;
    public ComboBox showBrands;
    public ComboBox showCategories;
    public Pane loginPane;
    public Button toRegister;
    public Pane shoppinCartP;
    public Button confirmOrder;
    public Pane shoesDescriptionP;
    public AnchorPane mainPage;
    public Button removeCart;
    public Button myPageB;
    public Button logout;
    private ObservableList<Shoes> shoesList;
    private ObservableList<Shoes> shoppingCart;
    private ShoesManagerImpl shoesManager = new ShoesManagerImpl();
    private CategoryManagerImpl categoryManager = new CategoryManagerImpl();
    private BrandManagerImpl brandManager = new BrandManagerImpl();
    private OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    public Utils utils;

    public void initialize() {
        utils = new Utils(mainPage);

        logout.setOnAction(e -> {
            UserLogin.getInstance().setLogged(false);
            try {
                utils.changeScene("HOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        myPageB.setOnAction(e -> {
            try {
                utils.changeScene("MYPAGESHOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        try {
            shoesList = FXCollections.observableArrayList(shoesManager.getAllShoes());
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
        }


        if (!UserLogin.getIsLogged()) {
            shoppinCartP.setVisible(false);
            logout.setText("Top");
        }
        else {
            loginPane.setVisible(false);
            loginL.setText("Welcome " + UserLogin.getCustomer().getName()); //we get here the customer name
            logout.setText("Logout");
        }


        //shoping cart
        shoppingCart = FXCollections.observableArrayList();
        cartPrice.setCellValueFactory(new PropertyValueFactory("price"));
        cartBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        cartQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        shoppingCartView.setItems(shoppingCart);
        removeCart.setOnAction(e -> {
            shoppingCartView.getItems().clear();
            totalPriceL.setText("");
            shoppingCart.removeAll();
        });
        shoppingCartView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
                shoppingCartView.getItems().remove(shoppingCartView.getSelectionModel().getSelectedItem());
            totalPriceL.setText("");
        });
        // log in with the singleton
        toRegister.setOnAction(e -> {
            try {
                utils.changeScene("REGISTER");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        startLogInB.disableProperty().bind(email.textProperty().isEmpty().or(passF.textProperty().isEmpty()));
        startLogInB.setOnAction(e -> {
            UserLogin.UserLogin(email.getText(), passF.getText());
            if (UserLogin.getIsLogged()) {
                loginL.setText("Welcome " + UserLogin.getCustomer().getName());
                loginPane.setVisible(false);
                shoppinCartP.setVisible(true);
            }
        });

        //Show in table-> brand, color, size,price,rating,category,quantity
        //TODO: implement rating to shoes. ta bort det
        c1.setCellValueFactory(new PropertyValueFactory("brandP"));
        c2.setCellValueFactory(new PropertyValueFactory("color"));
        c3.setCellValueFactory(new PropertyValueFactory("size"));
        c4.setCellValueFactory(new PropertyValueFactory("price"));
        //c5.setCellValueFactory(new PropertyValueFactory("rating"));
        c6.setCellValueFactory(new PropertyValueFactory("categoriesP"));
        c7.setCellValueFactory(new PropertyValueFactory("quantity"));
        c1.setText("Brand");
        c2.setText("Color");
        c3.setText("Size");
        c4.setText("Price");
        c5.setText("Rating");
        c6.setText("Category");
        c7.setText("On stock");
        try {
            showColors.itemsProperty().setValue(shoesManager.getColorList());
            showCategories.itemsProperty().setValue(FXCollections.observableArrayList(
                    categoryManager.getAllCategory().stream().map(Category::getName).collect(Collectors.toList())));
            showBrands.itemsProperty().setValue(
                    FXCollections.observableArrayList(brandManager.getAllBrand().stream().
                            map(Brand::getName).collect(Collectors.toList())));

            showColors.getSelectionModel().selectFirst();
            showCategories.getSelectionModel().selectFirst();
            showBrands.getSelectionModel().selectFirst();
            //using obs list

            //shoesList = QueryExec.getShoesList();
            shoesTable.setItems(shoesList);
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        showCategories.valueProperty().addListener(((observableValue, o, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1.toString()))));
        showBrands.valueProperty().addListener(((observableValue, o, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1.toString()))));

        showColors.valueProperty().addListener(((observableValue, o, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1.toString()))));
        searchField.textProperty().addListener(((observableValue, s, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1))));
        //select by click this can call to addToCart (or display a new pane asking for confirm to add to cart)
        shoesTable.setOnMouseClicked(e -> {
                    if (e.getClickCount() == 2)
                        loadShoesDesc(((Shoes) shoesTable.getSelectionModel().getSelectedItem()),
                                shoppingCart, totalPriceL);

                }
        );
        showTable.setOnAction(e -> {
            shoesTable.setItems(shoesList);
        });

        confirmOrder.setOnAction(e -> {
            try {
                int orderId = orderManager.getOrderIdFromAddTOCart(UserLogin.getCustomer().getId(), -1,
                        shoppingCart.get(0).getId(), shoppingCart.get(0).getQuantity(), 2);

                shoppingCart.stream().skip(1).forEach(s -> {
                    try {
                        orderManager.getAddTOCart(UserLogin.getCustomer().getId(), orderId, s.getId(),
                                s.getQuantity(), 2);
                    } catch (SQLException | IOException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });

                loadConfirmDialog(shoppingCart, orderId);
            } catch (IOException | SQLException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
            try {
                utils.changeScene("INVOICE");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }

    private void loadConfirmDialog(ObservableList<Shoes> shoppingCart, int orderId) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/confirmShopping.fxml"));
        Parent parent = fxmlLoader.load();
        ConfirmShopping dialogController = fxmlLoader.<ConfirmShopping>getController();
        dialogController.setData(shoppingCart, orderId);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    //adds the shoes description panel
    private void loadShoesDesc(Shoes shoesData, ObservableList<Shoes> shoppingCart, Label totalPrice) {
        shoesDescriptionP.getChildren().clear();
        Pane newLoadedPane = null;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/shoesDescription.fxml"));
            newLoadedPane = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ShoesDescription controller = loader.getController();
        controller.setData(shoesData, shoppingCart, totalPrice);
        shoesDescriptionP.getChildren().add(newLoadedPane);
    }


    private boolean isFound(Shoes shoesData, String searchText) {
        return (shoesData.getColor().toLowerCase().contains(searchText.toLowerCase())
                || shoesData.getBrand().getName().toLowerCase().contains(searchText.toLowerCase()) ||
                shoesData.getCategoriesP().toLowerCase().contains(searchText.toLowerCase()));
    }


    //method to search in observable list and update
    private ObservableList<Shoes> filteredList(ObservableList<Shoes> list, String searchText) {
        List<Shoes> filteredList = new ArrayList();
        for (Shoes shoesData : list) {
            if (isFound(shoesData, searchText)) filteredList.add(shoesData);
        }
        return FXCollections.observableArrayList(filteredList);
    }
}

