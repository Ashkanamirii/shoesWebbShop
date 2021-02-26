package controller;


import enumation.Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import model.bl.CustomerManagerImpl;
import model.bl.OrderLineItemManagerImpl;
import model.to.History;
import model.to.Invoice;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MyPagesOrders {
    @FXML
    public AnchorPane myPagesOrdersPane;
    public Button logout;
    public Button goToShopping;
    public Button myPagesBtn;
    public Button editUserProfileBtn;
    public Label loginL;
    public VBox surveyBtnBox;
    public Button searchBtn;
    public TableView<Invoice> invoiceTable;
    public TableColumn<Invoice, Integer> shoesNumberC;
    public TableColumn<Invoice, String> brandC;
    public TableColumn<Invoice, String> colorC;
    public TableColumn<Invoice, Double> priceC;
    public TableColumn<Invoice, Integer> quantityC;
    public TableView<History> ordersTable;
    public TableColumn<History, Integer> orderId;
    public TableColumn<History, String> orderDate;
    public Button updateOrderLine;
    public Label orderIdLabel;
    public TextField searchField;
    private ObservableList<Invoice> invoice;
    private ObservableList<History> userHistory;
    private final OrderLineItemManagerImpl orderManager = new OrderLineItemManagerImpl();
    private final CustomerManagerImpl customerManager = new CustomerManagerImpl();
    public Utils changeScene;

    public void initialize() {
        updateOrderLine.setText("UPDATE");
        searchField.textProperty().addListener(((observableValue, s, t1) ->
                ordersTable.setItems(filteredList(userHistory, t1))));

        try {
            List<History> historyList = customerManager.customerHistory(
                    UserLogin.getCustomer().getId())
                    .stream()
                    .filter(distinctByKey(History::getOrderId))
                    .collect(Collectors.toList());

            userHistory = FXCollections.observableArrayList(historyList);

        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println(Error.DATABASE);
            System.out.println(Error.CONNECTION);
        }
        ordersTable.setItems(userHistory);
        orderId.setCellValueFactory(new PropertyValueFactory("orderId"));
        orderDate.setCellValueFactory(new PropertyValueFactory("orderDate"));
        ordersTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {

                int orderId = ordersTable.getSelectionModel().getSelectedItem().getOrderId();
                orderIdLabel.setText(orderId + "");
                try {
                    invoice = FXCollections.observableArrayList(orderManager.getInvoice
                            (orderId));
                } catch (SQLException | IOException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                    System.out.println(Error.DATABASE);
                    System.out.println(Error.CONNECTION);
                }
                invoiceTable.setItems(invoice);
                invoiceTable.refresh();
            }
        });

        try {
            if (userHistory.size() >= 0) {
                invoice = FXCollections.observableArrayList(orderManager.getInvoice(userHistory.get(0).getOrderId()));
            }
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println(Error.DATABASE);
            System.out.println(Error.CONNECTION);
        }
        brandC.setCellValueFactory(new PropertyValueFactory("shoesBrandName"));
        colorC.setCellValueFactory(new PropertyValueFactory("shoesColor"));
        priceC.setCellValueFactory(new PropertyValueFactory("price"));
        shoesNumberC.setCellValueFactory(new PropertyValueFactory("shoesNumber"));
        quantityC.setCellValueFactory(new PropertyValueFactory("quantity"));

        invoiceTable.setItems(invoice);
        invoiceTable.setEditable(true);
        invoiceTable.setOnKeyPressed(e -> {
            TablePosition focus = invoiceTable.focusModelProperty().get().focusedCellProperty().get();
            invoiceTable.edit(focus.getRow(), focus.getTableColumn());

        });

        quantityC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        invoiceTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        ArrayList<Integer> quantityChangeLog = new ArrayList<>();
        quantityC.setOnEditCommit(e -> {
            quantityChangeLog.add(e.getOldValue());
            if (e.getNewValue() > quantityChangeLog.get(0)) {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setContentText(Error.RETURNED_FAILED.toString());
                dialog.showAndWait();
            } else {
                final Integer value = e.getNewValue() != null &&
                        quantityChangeLog.get(0).equals(
                                e.getOldValue()) ? e.getNewValue() : quantityChangeLog.get(0);
                (e.getTableView().getItems().get(e.getTablePosition().getRow())).setQuantity(value);
            }
            invoiceTable.refresh();
        });
        //send to be returned
        updateOrderLine.setOnAction(e -> {
            System.out.println(invoice.get(0).getOrderId());
            invoice.forEach(i -> {
                if (i.quantityToReturn() != 0) {
                    try {
                        System.out.println("ORDER ID--->" + i.getOrderId() + "update -- Quantity-->" + i.quantityToReturn());
                        orderManager.getAddTOCart(
                                UserLogin.getCustomer().getId(), i.getOrderId(),
                                i.getShoesId(), i.quantityToReturn(), 5);
                    } catch (SQLException | IOException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Returned action");
            alert.setContentText("We have taken your request ,\nYou will be charge with-->" +
                    invoice.stream().map(Invoice::multiplyPriceQuantity).reduce(0.0, Double::sum) + "Kr.");
            alert.showAndWait();


        });

        changeScene = new Utils(myPagesOrdersPane);
        loginL.setText("You login as " + UserLogin.getCustomer().getName());

        logout.setOnAction(e -> {
            try {
                changeScene.changeScene("HOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("SHOP");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        myPagesBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("MYPAGESHOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        editUserProfileBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("EDITPROFILE");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    private boolean isFound(History history, String searchText) {
        return (history.getShoesBrandName().toLowerCase().contains(searchText.toLowerCase())
                || history.getOrderDate().toLowerCase().contains(searchText.toLowerCase()) ||
                history.getShoesColor().toLowerCase().contains(searchText.toLowerCase()));
    }


    //method to search in observable list and update
    private ObservableList<History> filteredList(ObservableList<History> list, String searchText) {
        List<History> filteredList = new ArrayList();
        for (History history : list) {
            if (isFound(history, searchText)) filteredList.add(history);
        }
        return FXCollections.observableArrayList(filteredList);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}



