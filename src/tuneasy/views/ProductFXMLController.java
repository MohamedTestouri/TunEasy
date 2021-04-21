/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tuneasy.entities.Product;
import tuneasy.services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField subtitleTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button submitButton;

    String name, description, subtitle, image;
    int category;
    double prix;

    ProductService productService = new ProductService();
    int idProduct = productService.idProduct;
    boolean selected = productService.selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (selected) {
            Product product = productService.findById(idProduct);
            nameTextField.setText(product.getName());
            descriptionTextField.setText(product.getDescription());
            subtitleTextField.setText(product.getSubtitle());
            imageTextField.setText(product.getImage());
            categoryTextField.setText("" + product.getIdCategory());
            prixTextField.setText("" + product.getPrix());
        }
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name = nameTextField.getText();
                description = descriptionTextField.getText();
                subtitle = subtitleTextField.getText();
                image = imageTextField.getText();
                category = Integer.parseInt(categoryTextField.getText());
                prix = Double.parseDouble(prixTextField.getText());
                Product product = new Product(name, image, subtitle, description, prix, category);
                if (!selected) {
                    productService.addProduct(product);
                } else {
                    productService.updateProduct(idProduct, product);
                    productService.selected = false;
                }

            }
        });

    }

}
