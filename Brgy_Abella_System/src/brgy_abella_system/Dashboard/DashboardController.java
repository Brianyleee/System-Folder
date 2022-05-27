/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Dashboard;
import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    Functions DashboardModel = new Functions();
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
    @FXML
    private Button inquiriesBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Label Emp_Counter;
    @FXML
    private Label Blotter_Counter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Employeecounter();
            BlotterCounter();
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
    private void inquiriesBtnAction (ActionEvent event) throws IOException{
        action.ChangeScene("Inquiries/Inquiries.fxml", inquiriesBtn);
    }
    
    private void Employeecounter() throws SQLException{
        int count = DashboardModel.countExistingEmployee();
        Emp_Counter.setText(Integer.toString(count));
    }
    
    private void BlotterCounter() throws SQLException {
        int count = DashboardModel.countBlottersFiled();
        Blotter_Counter.setText(Integer.toString(count));
    }
}
