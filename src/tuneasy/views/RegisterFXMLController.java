/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tuneasy.entities.User;
import tuneasy.services.UserService;
import tuneasy.utils.LoginSession;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class RegisterFXMLController implements Initializable {
    
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField rePasswordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField roleTextField;
    
    UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                navigate("Login");
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkNotNull(firstNameTextField.getText()) && checkNotNull(lastNameTextField.getText()) && checkNotNull(imageTextField.getText()) && checkNotNull(roleTextField.getText()) && checkEmail(emailTextField.getText()) && checkPassword(passwordTextField.getText(), rePasswordTextField.getText())) {
                    User user = new User(emailTextField.getText(), passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), imageTextField.getText(), roleTextField.getText());
                    userService.signup(user);
                    LoginSession.idLoggedUser = userService.findIdUserByEmail(user.getEmail());
                    LoginSession.isLogged = true;
                    if (user.getRole().equals("admin")) {
                        navigate("Products");
                    } else {
                        navigate("Store");
                    }
                } else {
                    System.out.println("invalid inputs");
                }
            }
        });
    }
    
    public boolean checkNotNull(String name) {
        if (name.length() != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkPassword(String password, String rePassword) {
        if (password.length() > 8) {
            if (password.equals(rePassword)) {
                return true;
            } else {
                System.out.println("Password doesn't match");
                return false;
            }
        } else {
            System.out.println("Password is too short");
            return false;
        }
    }
    
    public boolean checkEmail(String email) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email);
        if (controler.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void navigate(String panel) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/" + panel + "FXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("TunEasy");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
