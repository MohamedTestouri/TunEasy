/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuneasy.entities.Product;
import tuneasy.utils.Connection;

/**
 *
 * @author weixin
 */
public class ProductService {

    private Connection connection = new Connection();
    public static int idProduct = 0;
    public static boolean selected = false;

    public void addProduct(Product product) {
        String query = "INSERT INTO product (category_id, name, image, subtitle, prix, description) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, product.getIdCategory());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setString(4, product.getSubtitle());
            preparedStatement.setDouble(5, product.getPrix());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(int idProduct, Product product) {
        String query = "UPDATE product SET category_id=?, name=?, image=?, subtitle=?, prix=?, description=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, product.getIdCategory());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setString(4, product.getSubtitle());
            preparedStatement.setDouble(5, product.getPrix());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, idProduct);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int idProduct) {
        String query = "DELETE FROM product WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product findById(int id) {
        Product product = new Product();
        String query = "SELECT * FROM product WHERE id = " + id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idCategory = resultSet.getInt("category_id");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double prix = resultSet.getDouble("prix");
                String subtitle = resultSet.getString("subtitle");
                product = new Product(idProduct, name, image, subtitle, description, prix, idCategory);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public ObservableList<Product> showProduct() {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        String query = "SELECT * FROM product";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int idCategory = resultSet.getInt("category_id");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double prix = resultSet.getDouble("prix");
                String subtitle = resultSet.getString("subtitle");
                Product product = new Product(idProduct, name, image, subtitle, description, prix, idCategory);
                productObservableList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productObservableList;
    }
}
