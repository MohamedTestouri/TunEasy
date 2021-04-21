/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tuneasy.entities.Order;
import tuneasy.services.OrderDetailsService;
import tuneasy.services.OrderService;
import static tuneasy.utils.Connection.connection;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class OrdersFXMLController implements Initializable {

    @FXML
    private TableView<Order> ordersTableView;
    @FXML
    private TableColumn<Order, String> idColumn;
    @FXML
    private TableColumn<Order, String> idUserColumn;
    @FXML
    private TableColumn<Order, String> descriptionColumn;
    @FXML
    private TableColumn<Order, String> totalColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private Button updateStatusButton;
    @FXML
    private Button showDetailsButton;

    OrderService orderService = new OrderService();
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayOrders();
        updateStatusButton.setOnAction((t) -> {
            orderService.updateOrderStatus(ordersTableView.getSelectionModel().getSelectedItem().getId());
        });
        showDetailsButton.setOnAction((t) -> {
            OrderDetailsService.idOrder = ordersTableView.getSelectionModel().getSelectedItem().getId();
            navigate();
        });

    }

    public void displayOrders() {
        //
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ordersTableView.setItems(orderService.showOrders());
    }

    public void navigate() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/OrderDetailsFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update Product");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<PieChart.Data> getData() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        try {
            String query = "SELECT COUNT(DISTINCT id) AS pending FROM commande WHERE status = pending";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new PieChart.Data("Pending", resultSet.getInt("pending")));
                System.out.println(resultSet.getInt("pending"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String query = "SELECT COUNT(DISTINCT id) AS delivered FROM commande WHERE status = delivered";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new PieChart.Data("Delivered", resultSet.getInt("delivered")));
                System.out.println(resultSet.getInt("delivered"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
