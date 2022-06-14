package brgy_abella_system.Register;
import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;


public class RegisterController implements Initializable {

    Connection Connect;

    public RegisterController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    
    Repeatables action = new Repeatables();
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
        action.Exit(ExitBtn);
    }

    @FXML
    private void RegisterButtonAction(ActionEvent event) throws SQLException, IOException {
        String Emp_Id = EmpId_Register.getText();
        String Username = Username_Register.getText();
        String Password = Password_Register.getText();
        String CPassword = PasswordConfirm_Register.getText();
        if (!Emp_Id.isEmpty() && !Username.isEmpty() && !Password.isEmpty() && !CPassword.isEmpty()) {
            if (isEmpIdEsxisting(Emp_Id)) {
                if (isAccountExisting(Emp_Id)) {
                    if (isValidAccount(Emp_Id)) {
                            if (isSamePassword(Password, CPassword)) {
                                if (InsertAccount(Emp_Id, Username, Password)) {
                                    if (InsertUsernameEmployee(Username, Emp_Id)) {
                                        RegisterStatus.setText("Account Has been Added");
                                        action.ChangeScene("Login/Login.fxml", RegisterBtn);
                                    } else {
                                        RegisterStatus.setText("Account Was Not Added");
                                    }
                                } else {
                                    RegisterStatus.setText("Username Must be Unique");
                                }
                            } else {
                                RegisterStatus.setText("Password did not match");
                            }
                    } else {
                        RegisterStatus.setText("You Have no Access Rights to Create Valid Account");
                    }
                } else {
                    RegisterStatus.setText("Employee has an Existing Account");
                }
            } else {
                RegisterStatus.setText("Employee Id is not Existing");
            }

        } else {
            RegisterStatus.setText("Fill out the Requirements");
        }

    }

    @FXML
    private void CancelButtonAction(ActionEvent event) throws IOException {
        action.ChangeScene("Login/Login.fxml", CancelBtn);
    }
    
    //  Registration Validating if the Employee Id can create an Account based on its access level that was given by the admin.
    public boolean isValidAccount(String Emp_Id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Employee_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Emp_Id);
            rs = ps.executeQuery();
            if (rs.getInt("Access") == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            rs.close();
        }
    }

//    Registration Validating if the Password and The Confirm password is the same.
    public boolean isSamePassword(String Password, String CPassword) {
        if (Password.equals(CPassword)) {
            return true;
        } else {
            return false;
        }
    }

//    Registration Validating if the Employee Id has an Existing Account.
    public boolean isAccountExisting(String Emp_Id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Employee_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Emp_Id);
            rs = ps.executeQuery();
            if (rs.getString("Username") == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            rs.close();
        }
    }

//    Registration Validating if the Employee-Id is Existing.
    public boolean isEmpIdEsxisting(String Emp_Id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Employee_Id FROM Employee WHERE Employee_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Emp_Id);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            rs.close();
        }
    }

//    Registration Validating if the Username was Already taken.
    public boolean isUsernameExisting(String Username) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Username=?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            ps.close();
            rs.close();
        }
    }
    
    //    Adding the registered Account in the Database
    public boolean InsertAccount(String Emp_Id, String Username, String Password) throws SQLException {
        PreparedStatement ps = null;
        String query = "INSERT INTO Account (Username,Password) VALUES (?,?)";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Password);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
        }
    }

//    Adding the Username as Foreign key in Employee Table
    public boolean InsertUsernameEmployee(String Username, String Emp_Id) throws SQLException {
        PreparedStatement ps = null;
        String query = "UPDATE Employee SET Username=? WHERE Employee_Id=?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Emp_Id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            ps.close();
        }
    }
    
}
