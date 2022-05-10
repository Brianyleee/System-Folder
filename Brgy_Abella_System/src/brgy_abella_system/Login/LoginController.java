
package brgy_abella_system.Login;
import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

public class LoginController implements Initializable {
    public Functions LoginModel = new Functions();   
    Repeatables action = new Repeatables();
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button LoginBtn;
    @FXML
    private Button CreateBtn;
    @FXML
    private Label LoginStatus;
    @FXML
    private Button ExitBtn;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LoginButtonAction(ActionEvent event) throws SQLException, IOException {
        String username = Username.getText();
        String password = Password.getText();
        if (LoginModel.isLogin(username, password)) {
            action.ChangeScene("Dashboard/Dashboard.fxml", LoginBtn);
        } else {
            LoginStatus.setText("Invalid Username / Password");
        }
    }

    @FXML
    public void CreateButtonAction(ActionEvent event) throws IOException {
        String FXMLname = "Register/Register.fxml";
        Button BtnName = CreateBtn;
        action.ChangeScene(FXMLname, BtnName);
    }

    @FXML
    public void ExitButtonAction(ActionEvent event) {
        action.Exit(ExitBtn);
    } 

}
