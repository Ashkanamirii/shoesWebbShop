package controller;

import connection.QueryExec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modell.Shoes;
import org.controlsfx.control.Rating;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebbshopPage {
    @FXML
    public Pane mainPane;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;
    public TableColumn c4;
    public TableColumn c5;
    public TableColumn c6;
    public TableColumn c7;
    public TableView tableTest;
    public ComboBox showColors;
    public Button showTable;
    public TextField searchField;
    public Button startLogInB;
    public Label loginL;

    public PasswordField passF;
    public TextField email;
    public Rating ratingTest;
    public TableView shoppingCartView;
    public TableColumn cartId;
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
    public Utils utils;

    private ObservableList<Shoes> shoesList;
    private FilteredList<Shoes> shoesFiltered;
    private ObservableList<Shoes> shoppingCart;

    public void initialize() {
        utils = new Utils();
        if(!UserLogin.getIsLogged())
            shoppinCartP.setVisible(false);
        else{
            loginPane.setVisible(false);
            loginL.setText("you are logged as " ); //we get here the customer name
        }




        //shoping cart
        shoppingCart = FXCollections.observableArrayList();
        cartId.setCellValueFactory(new PropertyValueFactory("id"));
        cartPrice.setCellValueFactory(new PropertyValueFactory("price"));
        cartBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        cartQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        shoppingCartView.setItems(shoppingCart);
        cartQuantity.setEditable(true);


        //testing rating
        ratingTest.setOrientation(Orientation.VERTICAL);

        // log in with the singleton
        toRegister.setOnAction(e -> {
            try {
                utils.changeScene("/registerCustomer.fxml", mainPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        //testing the log in with the singleton
        startLogInB.setOnAction(e -> {

            UserLogin.getInstance(email.getText(), passF.getText());
            if (UserLogin.getIsLogged()) {
                loginL.setText("You are logged in!!!!");
                loginPane.setVisible(false);
                shoppinCartP.setVisible(true);
            }
            ;
        });



        System.out.println(QueryExec.returnList("SELECT * FROM shoes;").toString());

     //Show in table-> brand, color, size,price,rating,category,quantity
        //TODO: implement rating and category to shoes.

        c1.setCellValueFactory(new PropertyValueFactory("brand"));
        c2.setCellValueFactory(new PropertyValueFactory("color"));
        c3.setCellValueFactory(new PropertyValueFactory("size"));
        c4.setCellValueFactory(new PropertyValueFactory("price"));
        //c5.setCellValueFactory(new PropertyValueFactory("rating"));
        //c6.setCellValueFactory(new PropertyValueFactory("category"));
        c7.setCellValueFactory(new PropertyValueFactory("quantity"));

        c1.setText("Brand");
        c2.setText("Color");
        c3.setText("Size");
        c4.setText("Price");
        c5.setText("Rating");
        c6.setText("Category");
        c7.setText("On stock");


        try {
            showColors.itemsProperty().setValue(QueryExec.getColorsList());
            showCategories.itemsProperty().setValue(QueryExec.getCategoriesList());
            showBrands.itemsProperty().setValue(QueryExec.getBrandList());
            //using obs list

            shoesList = QueryExec.getShoesList();
            shoesTable.setItems(shoesList);


        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        showBrands.valueProperty().addListener(((observableValue, o, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1.toString()))));
        showColors.valueProperty().addListener(((observableValue, o, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1.toString()))));
        searchField.textProperty().addListener(((observableValue, s, t1) ->
                shoesTable.setItems(filteredList(shoesList, t1))));

        //select by click this can call to addToCart (or display a new pane asking for confirm to add to cart)
        shoesTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
                loadShoesDesc(((Shoes) shoesTable.getSelectionModel().getSelectedItem()),shoppingCart,totalPriceL);

                        }
                    });
                } else {
                    a.setContentText("you choose this id ->" +
                            ((Shoes) tableTest.getSelectionModel().getSelectedItem()).getId() + "\n" +
                            "YOU ARE NOT LOGGED IN, go and logg in to enjoy your shopping");
                    a.show();
                }

            }
        });

        showTable.setOnAction(e -> {
            shoesTable.setItems(shoesList);
        });

    }


    //adds the shoes description panel
    public void loadShoesDesc (Shoes shoesData, ObservableList<Shoes> shoppingCart, Label totalPrice)  {
        shoesDescriptionP.getChildren().clear();
        Pane newLoadedPane = null;
        FXMLLoader loader=new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/shoesDescription.fxml"));
            newLoadedPane=loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ShoesDescription controller=loader.getController();
        controller.setData(shoesData,shoppingCart,totalPriceL);
        shoesDescriptionP.getChildren().add(newLoadedPane);
    }

//Now searches only color and brand
    private boolean isFound(Shoes shoesData, String searchText) {
        return (shoesData.getColor().toLowerCase().contains(searchText)
                || shoesData.getBrand().toLowerCase().contains(searchText));
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
