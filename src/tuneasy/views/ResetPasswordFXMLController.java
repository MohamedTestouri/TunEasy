/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.views;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tuneasy.services.UserService;
import tuneasy.utils.SendMail;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ResetPasswordFXMLController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField codeTextField;
    @FXML
    private Button sendCodeButton;
    @FXML
    private Button verifyCodeButton;
    @FXML
    private Button resetPasswordButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField rePasswordTextField;

    UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sendCodeButton.setOnAction((t) -> {
            if (checkEmail(emailTextField.getText())) {
                if (userService.findUserByEmail(emailTextField.getText())) {
                    verifyCodeButton.setDisable(false);
                    codeTextField.setDisable(false);
                    userService.generateCode(emailTextField.getText(), "code");
                    try {
                        SendMail.sendMail(emailTextField.getText(), "code");
                        // send code here
                    } catch (Exception ex) {
                        Logger.getLogger(ResetPasswordFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Exeception: " + ex);
                    }
                }
            }
        });
        verifyCodeButton.setOnAction((t) -> {
            if (codeTextField.getText().equals("")) {
                if (userService.verifyCode(emailTextField.getText(), codeTextField.getText())) {
                    passwordTextField.setDisable(false);
                    rePasswordTextField.setDisable(false);
                    resetPasswordButton.setDisable(false);
                }
            }
        });
        resetPasswordButton.setOnAction((t) -> {
            if (checkPassword(passwordTextField.getText(), rePasswordTextField.getText())) {
                userService.resetPassword(emailTextField.getText(), passwordTextField.getText());
            }
        });

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
}
