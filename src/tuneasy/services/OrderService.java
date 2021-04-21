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
import tuneasy.entities.Order;
import tuneasy.utils.Connection;

/**
 *
 * @author weixin
 */
public class OrderService {

    private Connection connection = new Connection();

    public void addOrder(Order order) {
        String query = "INSERT INTO commande (idUser, description, total) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateOrderStatus(int id) {
        String query = "UPDATE commande SET status= delivered WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Order> showOrders() {
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM commande";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String description = resultSet.getString("description");
                double total = resultSet.getDouble("total");
                String status = resultSet.getString("status");
                Order order = new Order(id, idUser, description, total, status);
                orderObservableList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderObservableList;
    }

    public int findLastOrder() {
        String query = "SELECT * FROM commande";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int id = resultSet.getInt("id");
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
