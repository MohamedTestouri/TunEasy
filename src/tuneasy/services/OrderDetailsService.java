/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuneasy.entities.OrderDetails;
import tuneasy.utils.Connection;

/**
 *
 * @author weixin
 */
public class OrderDetailsService {
     private Connection connection = new Connection();
public static int idOrder = 0;
    public void addOrderDetails(OrderDetails orderDetails) {
        String query = "INSERT INTO commandeDetails (idProduct, idOrder, quantity, total) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, orderDetails.getIdProduct());
            preparedStatement.setInt(2, orderDetails.getIdOrder());
            preparedStatement.setInt(3, orderDetails.getQuantity());
            preparedStatement.setDouble(4, orderDetails.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<OrderDetails> showOrderDetails() {
        ObservableList<OrderDetails> orderDetailsObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM commandeDetails";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduct = resultSet.getInt("idProduct");
                int idOrder = resultSet.getInt("idOrder");
                int quantity = resultSet.getInt("quantity");
                double total = resultSet.getDouble("total");
                OrderDetails orderDetails = new OrderDetails(id, idProduct, idOrder, quantity, total);
                orderDetailsObservableList.add(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailsObservableList;
    }
    public ObservableList<OrderDetails> showOrderDetailsByIdOrder(int idOrders) {
        ObservableList<OrderDetails> orderDetailsObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM commandeDetails WHERE idOrder="+idOrders;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduct = resultSet.getInt("idProduct");
                int idOrder = resultSet.getInt("idOrder");
                int quantity = resultSet.getInt("quantity");
                double total = resultSet.getDouble("total");
                OrderDetails orderDetails = new OrderDetails(id, idProduct, idOrder, quantity, total);
                orderDetailsObservableList.add(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailsObservableList;
    }
}
