package brgy_abella_system;

import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Functions {

    Connection Connect;

    public Functions() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
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
