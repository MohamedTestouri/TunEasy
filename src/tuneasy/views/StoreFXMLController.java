/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tuneasy.entities.Order;
import tuneasy.entities.OrderDetails;
import tuneasy.entities.Product;
import tuneasy.services.OrderDetailsService;
import tuneasy.services.OrderService;
import tuneasy.services.ProductService;
import tuneasy.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class StoreFXMLController implements Initializable {

    @FXML
    private TableView<OrderDetails> orderTableView;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> subtitleColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> prixColumn;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Button addQuabtityButton;
    @FXML
    private Label totalLabel;

    ProductService productService = new ProductService();
    OrderDetailsService orderDetailsService = new OrderDetailsService();
    OrderService orderService = new OrderService();
    OrderDetails orderDetails;
    @FXML
    private Button orderButton;
    private TextField descriptionTextView;
    @FXML
    private TextField descriptionTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalLabel.setText("0");
        displayProducts();
        addQuabtityButton.setOnAction((t) -> {
            int idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            int quantity = Integer.parseInt(quantityTextField.getText());
            double total = quantity * productTableView.getSelectionModel().getSelectedItem().getPrix();
            orderDetails = new OrderDetails(idProduct, 0, quantity, total);
            double totalOrder = Double.parseDouble(totalLabel.getText()) + total;
            totalLabel.setText(totalOrder + "");

        });
        orderButton.setOnAction((t) -> {
            Order order = new Order(LoginSession.idLoggedUser, descriptionTextField.getText(), Double.parseDouble(totalLabel.getText()));
            orderService.addOrder(order);
            saveOrderDetails();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ordering");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been successfully saved!");
            alert.showAndWait();
            orderTableView.getItems().clear();
        });
    }

    public void displayProducts() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void saveOrderDetails() {
        ObservableList<OrderDetails> orderDetailsObservableList = orderTableView.getItems();
        for (int i = 0; i < orderDetailsObservableList.size(); i++) {
            orderDetailsObservableList.get(i).setIdOrder(orderService.findLastOrder());
            orderDetailsService.addOrderDetails(orderDetailsObservableList.get(i));
        }
    }

}
