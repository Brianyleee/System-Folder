package brgy_abella_system;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Functions {
ZoneId defaultZoneId = ZoneId.systemDefault();
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
    
//    Existing Employee Id in Employee List
    public boolean isEmployeeId(String Id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Employee_Id=?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Id);
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
    
//    Insert Whole Employee Added
    public boolean InsertEmployee(String Emp_Id, String fname,String mname,String lname,String Designation,LocalDate DOB,LocalDate Hired,LocalDate Resign,String Status,int Access) throws SQLException {
        PreparedStatement ps = null;
        String query = "INSERT INTO Employee (Employee_Id,Access,Designation,First_Name,Middle_Name,Last_name,DOB,Date_Hired,Date_Resigned,Status) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Emp_Id);
            ps.setInt(2, Access);
            ps.setString(3, Designation);
            ps.setString(4, fname);
            ps.setString(5, mname);
            ps.setString(6, lname);
            ps.setString(7, DOB.toString());
            ps.setString(8, Hired.toString());
            if(Resign == null){
                String Resign2 = null;
                ps.setString(9, Resign2);
            }else{
                ps.setString(9, Resign.toString());
            }          
            ps.setString(10, Status);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
        }
    }
}
