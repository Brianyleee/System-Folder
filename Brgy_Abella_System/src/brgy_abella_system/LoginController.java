
package brgy_abella_system;

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
    public Model LoginModel = new Model();   
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
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage window = (Stage) LoginBtn.getScene().getWindow();
            window.setScene(new Scene(root));
            LoginModel.Draggable(window, root);
        } else {
            LoginStatus.setText("Invalid Username / Password");
        }
    }

    @FXML
    public void CreateButtonAction(ActionEvent event) throws IOException {
        String FXMLname = "Register.fxml";
        Button BtnName = CreateBtn;
        LoginModel.ChangeScene(FXMLname, BtnName);
    }

    @FXML
    public void ExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) ExitBtn.getScene().getWindow();
        stage.close();
    }


    

}
