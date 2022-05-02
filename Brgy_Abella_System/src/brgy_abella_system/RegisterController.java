package brgy_abella_system;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class RegisterController implements Initializable {

    public Model RegisterModel = new Model();
    @FXML
    private Button ExitBtn;
    @FXML
    private TextField EmpId_Register;
    @FXML
    private PasswordField Password_Register;
    @FXML
    private Button RegisterBtn;
    @FXML
    private TextField Username_Register;
    @FXML
    private PasswordField PasswordConfirm_Register;
    @FXML
    private Label RegisterStatus;
    @FXML
    private Button CancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) ExitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void RegisterButtonAction(ActionEvent event) throws SQLException, IOException {
        String Emp_Id = EmpId_Register.getText();
        String Username = Username_Register.getText();
        String Password = Password_Register.getText();
        String CPassword = PasswordConfirm_Register.getText();
        if (!Emp_Id.isEmpty() || !Username.isEmpty() || !Password.isEmpty() || !CPassword.isEmpty()) {
            if (RegisterModel.isAccountExisting(Emp_Id)) {
                if (RegisterModel.isValidAccount(Emp_Id)) {
                    if (RegisterModel.isUsernameExisting(Username)) {
                        if (RegisterModel.isSamePassword(Password, CPassword)) {
                            if (RegisterModel.InsertAccount(Emp_Id, Username, Password)) {
                                if (RegisterModel.InsertUsernameEmployee(Username, Emp_Id)) {
                                    RegisterStatus.setText("Account Has been Added");
                                } else {
                                    RegisterStatus.setText("Account Was Not Added");
                                }
                            } else {
                                RegisterStatus.setText("Account Was Not Added");
                            }
                        } else {
                            RegisterStatus.setText("Password did not match");
                        }
                    } else {
                        RegisterStatus.setText("Username is Taken");
                    }
                } else {
                    RegisterStatus.setText("You Have no Access Rights to Create Valid Account");
                }
            } else {
                RegisterStatus.setText("Employee has an Existing Account");
            }
        } else {
            RegisterStatus.setText("Fill out the Requirements");
        }

    }

    @FXML
    private void CancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) CancelBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
