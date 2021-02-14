package controller;

import connection.QueryExec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public Button register;
    public Utils utils;
    public AnchorPane mainPage;


    private ObservableList<Shoes> shoesList;
    private FilteredList<Shoes> shoesFiltered;
    private ObservableList<Shoes> shoppingCart;

    public void initialize() {
        utils = new Utils();
        //testing shopping cart
        shoppingCart = FXCollections.observableArrayList();
        cartId.setCellValueFactory(new PropertyValueFactory("id"));
        cartPrice.setCellValueFactory(new PropertyValueFactory("price"));
        cartBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        cartQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        shoppingCartView.setItems(shoppingCart);
        cartQuantity.setEditable(true);


        //testing rating
        ratingTest.setOrientation(Orientation.VERTICAL);

        register.setOnAction(e -> {
            try {
                utils.changeScene("/registerCustomer.fxml", mainPage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        //testing the log in with the singleton
        startLogInB.setOnAction(e -> {

            UserLogin.getInstance(email.getText(), passF.getText());
            if (UserLogin.getIsLogged()) {
                loginL.setText("YOU are Logged IN!!!!");
                ratingTest.setVisible(true);
            }
            ;
        });


        System.out.println(QueryExec.returnList("SELECT * FROM shoes;").toString());

        tableTest.setVisible(false);


        //QueryExec q = new QueryExec();
//c1.setCellValueFactory(new PropertyValueFactory("id"));
        c2.setCellValueFactory(new PropertyValueFactory("size"));
        c3.setCellValueFactory(new PropertyValueFactory("shoes_number"));
        c4.setCellValueFactory(new PropertyValueFactory("brand"));
        c5.setCellValueFactory(new PropertyValueFactory("color"));
        c6.setCellValueFactory(new PropertyValueFactory("price"));
        c7.setCellValueFactory(new PropertyValueFactory("quantity"));

        c1.setText("id");
        c2.setText("Size");
        c3.setText("Shoes_number");
        c4.setText("Brand");
        c5.setText("Color");
        c6.setText("Price");
        c7.setText("Quantity");


        try {
            showColors.itemsProperty().setValue(QueryExec.returnQueryToList("select distinct color from shoes;"));

            //using obs list

            shoesList = QueryExec.returnList("SELECT shoes.id as id,size,shoes_number," +
                    "br.name as FK_brand_id,color,price,quantity\n" +
                    " FROM shoes\n" +
                    " join brand br on br.id =FK_brand_id;");
            tableTest.setItems(shoesList);


        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }


//using observable list

        showColors.valueProperty().addListener(((observableValue, o, t1) ->
                tableTest.setItems(filteredList(shoesList, t1.toString()))));
        searchField.textProperty().addListener(((observableValue, s, t1) ->
                tableTest.setItems(filteredList(shoesList, t1))));

        //select by click this can call to addToCart (or display a new pane asking for confirm to add to cart)
        tableTest.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                System.out.println(((Shoes) tableTest.getSelectionModel().getSelectedItem()).getId());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Do you want to add this item to the cart?");


                //TODO: fix userPrivileges mess
                if (UserLogin.getIsLogged() == true) {
                    a.setContentText("you choose this id ->" + ((Shoes) tableTest.getSelectionModel().
                            getSelectedItem()).getId());

                    //add to cart when click OK
                    a.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            Shoes newShoes = shoesList.get(((Shoes) tableTest.getSelectionModel().
                                    getSelectedItem()).getId() - 1);
                            newShoes.setQuantity(1);
                            shoppingCart.add(newShoes);
                            //sum price
                            totalPriceL.setText(shoppingCart.stream().map(Shoes::getPrice).
                                    reduce(0, Integer::sum).toString());

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
            tableTest.setVisible(true);
            // tableTest.setItems(shoesList);
            // shoesFiltered.setPredicate(shoesPredicate(""));
        });

    }


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
