/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Employee;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class AddEmployeeController implements Initializable {
    
    Connection Connect;

    public AddEmployeeController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }

    Repeatables action = new Repeatables();
    ObservableList<String> StatusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<Integer> Access = FXCollections.observableArrayList(0, 1);
    
    @FXML
    private TextField Emp_First_Name;
    @FXML
    private TextField Emp_Middle_Name;
    @FXML
    private TextField Emp_Last_Name;
    @FXML
    private ComboBox<String> Emp_Status;
    @FXML
    private DatePicker Emp_Birth_Date;
    @FXML
    private TextField Emp_Designation;
    @FXML
    private ComboBox<Integer> Emp_Access;
    @FXML
    private DatePicker Emp_Date_Hired;
    @FXML
    private DatePicker Emp_Date_Resigned;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Alert;
    @FXML
    private TextField Emp_id;
    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Emp_Status.setItems(StatusList);
        Emp_Access.setItems(Access);
    }

    @FXML
    public void ExitButtonAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }

    @FXML
    public void SaveButtonAction(ActionEvent event) throws SQLException, IOException {
        String fname = Emp_First_Name.getText().toUpperCase();
        String mname = Emp_Middle_Name.getText().toUpperCase();
        String lname = Emp_Last_Name.getText().toUpperCase();
        String id = Emp_id.getText().toUpperCase();
        LocalDate DOB = Emp_Birth_Date.getValue();
        String Designation = Emp_Designation.getText().toUpperCase();
        LocalDate Hired = Emp_Date_Hired.getValue();
        LocalDate Resign = Emp_Date_Resigned.getValue();
        
        String Status;
        if(Emp_Status.getValue() == null){
            Status = "";
        }else{
            Status = Emp_Status.getValue().toUpperCase();
        }
        
        int Access = 3;
        if (Emp_Access.getValue() != null) {
            Access = Emp_Access.getValue();
        }
        
        
        if (!fname.isEmpty() && !lname.isEmpty() && !id.isEmpty() && DOB != null && !Designation.isEmpty() && Hired != null && !Status.isEmpty() && Access != 3) {
            System.out.println( Access != 3);
            if (!isEmpIdEsxisting(id)) {
                if (InsertEmployee(id, fname, mname, lname, Designation, DOB, Hired, Resign, Status, Access)) {
                    Alert.setText("Employee Added");
                    action.Exit(saveBtn);
                } else {
                    Alert.setText("Fill out the Necessary Information (*)");
                }
            } else {
                Alert.setText("Employee Id Already Taken");
            }
        } else {
            Alert.setText("Fill out the Necessary Information (*)");
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




