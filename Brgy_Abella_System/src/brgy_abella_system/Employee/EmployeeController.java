package brgy_abella_system.Employee;

import brgy_abella_system.Connector;
import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class EmployeeController implements Initializable {

    Connection Connect;
    @FXML
    private Button addEmployeeBtn;
     @FXML
    private Button LogoutBtn;
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
    private TableColumn<Employee, String> emp_id;
    @FXML
    private TableColumn<Employee, String> position;
    @FXML
    private TableColumn<Employee, String> fname;
    @FXML
    private TableColumn<Employee, String> mname;
    @FXML
    private TableColumn<Employee, String> lname;
    @FXML
    private TableView<Employee> employeeTable;

    public EmployeeController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }

    public Functions EmployeeModel = new Functions();
    Repeatables action = new Repeatables();
    ObservableList<Employee> EmployeeList = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LogoutButtonAction(ActionEvent event) throws IOException {
        action.logout(LogoutBtn);
    }

    @FXML
    private void homeBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Dashboard/Dashboard.fxml", homeBtn);
    }

    @FXML
    private void residentBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Resident/Resident.fxml", residentBtn);
    }

    @FXML
    private void employeeBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Employee/Employee.fxml", employeeBtn);
    }

    @FXML
    private void blotterBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Blotter/Blotter.fxml", blotterBtn);
    }

    @FXML
    private void inquiriesBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Inquiries/Inquiries.fxml", inquiriesBtn);
    }
    @FXML
    private void AddBtnAction(ActionEvent event)throws IOException{
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        window.setScene(new Scene(root));
        window.initStyle(StageStyle.UNDECORATED);
        action.Draggable(window, root);
        window.show();
    }

    private void loadData() throws SQLException {
        refreshTable();
        emp_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        position.setCellValueFactory(new PropertyValueFactory<Employee,String>("Position"));
        fname.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        mname.setCellValueFactory(new PropertyValueFactory<Employee,String>("MiddleName"));
        lname.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        
    }

    private void refreshTable() throws SQLException {
        EmployeeList.clear();
        String query = "SELECT * FROM Employee";
        PreparedStatement ps = Connect.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        String id, fullname, position, fname, mname, lname = null;
        while (rs.next()) {
            id = rs.getString("Employee_Id");
            fname = rs.getString("First_Name");
            mname = rs.getString("Middle_Name");
            lname = rs.getString("Last_Name");
            position = rs.getString("Designation");

            EmployeeList.add(new Employee(id, fname,mname,lname, position));
            employeeTable.setItems(EmployeeList);
        }
    }
}
