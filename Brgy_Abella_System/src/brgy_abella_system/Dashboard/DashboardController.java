/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Dashboard;
import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class DashboardController implements Initializable {
    
    Connection Connect;

    public DashboardController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }

    Repeatables action = new Repeatables();

    private BorderPane BorderPane;
       @FXML
    private Button homeBtn;
    @FXML
    private Button residentBtn;
    @FXML
    private Button employeeBtn;
    @FXML
    private Button blotterBtn;
    private Button inquiriesBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Label Emp_Counter;
    @FXML
    private Label Blotter_Counter;
    @FXML
    private Button financialAidBtn;
    @FXML
    private Label Financial_Counter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Employeecounter();
            BlotterCounter();
            FinancialAidCounter();
        } catch (SQLException ex) {
            System.exit(0);
        }
    }

    @FXML
    private void LogoutButtonAction(ActionEvent event) throws IOException {
        action.logout(LogoutBtn);
    }
    @FXML
    private void homeBtnAction(ActionEvent event) throws IOException{
        action.ChangeScene("Dashboard/Dashboard.fxml", homeBtn);
    }
    @FXML
    private void residentBtnAction (ActionEvent event) throws IOException{
        action.ChangeScene("Resident/Resident.fxml", residentBtn);
    }
    @FXML
    private void employeeBtnAction (ActionEvent event) throws IOException{
        action.ChangeScene("Employee/Employee.fxml", employeeBtn);
    }
    @FXML
    private void blotterBtnAction (ActionEvent event) throws IOException{
        action.ChangeScene("Blotter/Blotter.fxml", blotterBtn);
    }
    @FXML
    private void financialAidBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("FinancialAid/FinancialAid.fxml", financialAidBtn);
    }
    
    private void Employeecounter() throws SQLException{
        int count = countExistingEmployee();
        Emp_Counter.setText(Integer.toString(count));
    }
    
    private void BlotterCounter() throws SQLException {
        int count = countBlottersFiled();
        Blotter_Counter.setText(Integer.toString(count));
    }

    private void FinancialAidCounter() throws SQLException{
        int count = countFinancialAidFiled();
        Financial_Counter.setText(Integer.toString(count));
    }
    
     public int countExistingEmployee() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Employee_Id FROM Employee";
        int index = 0;
        try {
            ps = Connect.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                index++;
            }
            return index;
        } catch (Exception e) {
            index = 0;
            return index;
        } finally {
            ps.close();
            rs.close();
        }
    }
    

    

    
//    Counts the number of total Blotters filed  
    public int countBlottersFiled() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Case_No FROM Blotter";
        int index = 0;
        try {
            ps = Connect.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                index++;
            }
            return index;
        } catch (Exception e) {
            index = 0;
            return index;
        } finally {
            ps.close();
            rs.close();
        }
    }
    
    public int countFinancialAidFiled() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Recipient_Id FROM Financial_Aid";
        int index = 0;
        try {
            ps = Connect.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                index++;
            }
            return index;
        } catch (Exception e) {
            index = 0;
            return index;
        } finally {
            ps.close();
            rs.close();
        }
    }
    
}
