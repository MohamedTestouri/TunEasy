/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tuneasy.entities.OrderDetails;
import tuneasy.services.OrderDetailsService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class OrderDetailsFXMLController implements Initializable {
    
    @FXML
    private TableColumn<OrderDetails, String> idColumn;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> idOrderColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
    @FXML
    private TableView<OrderDetails> orderDetailsTableView;
    
    OrderDetailsService orderDetailsService = new OrderDetailsService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayOrderDetails();
    }    

    public void displayOrderDetails() {
        //
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderDetailsTableView.setItems(orderDetailsService.showOrderDetailsByIdOrder(OrderDetailsService.idOrder));
    }    
}
