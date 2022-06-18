
package brgy_abella_system.Login;
import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;


import javafx.fxml.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


public class LoginController implements Initializable {
    
    Connection Connect;

    public LoginController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
      
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
        if (isLogin(username, password)) {
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
    
    //    Login Functionality Validating if the username and password are the same with the one in the database.
    public boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
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

}
