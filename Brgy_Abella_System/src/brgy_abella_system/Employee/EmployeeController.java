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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeController implements Initializable {

    ObservableList<Employee> EmployeeList = FXCollections.observableArrayList();
    Functions EmployeeModel = new Functions();
    Repeatables action = new Repeatables();

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
    @FXML
    private TextField searchBar;
    @FXML
    private Button RefreshBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button viewBtn;

    public EmployeeController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }

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
    private void AddBtnAction(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        window.setScene(new Scene(root));
        window.initStyle(StageStyle.UNDECORATED);
        action.Draggable(window, root);
        window.show();
    }

    public void loadData() throws SQLException {
        refreshTable();
        emp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        mname.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    private void refreshTable() throws SQLException {
        EmployeeList.clear();
        String query = "SELECT * FROM Employee";
        PreparedStatement ps = Connect.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id, fullname, position, fname, mname, lname;
            id = rs.getString("Employee_Id");
            fname = rs.getString("First_Name");
            mname = rs.getString("Middle_Name");
            lname = rs.getString("Last_Name");
            position = rs.getString("Designation");
            EmployeeList.add(new Employee(id, fname, mname, lname, position));
            employeeTable.setItems(EmployeeList);
        }
    }

    @FXML
    private void RefreshBtnAction(ActionEvent event) throws SQLException {
        loadData();
    }

    @FXML
    private void SearchData() throws SQLException {
        String Search = searchBar.getText();
        EmployeeList.clear();
        String query = "SELECT * FROM Employee WHERE Employee_Id LIKE ? OR"
                + " First_Name LIKE ? OR"
                + " Middle_Name LIKE ? OR"
                + " Last_Name LIKE ? OR"
                + " Designation Like ?";
        PreparedStatement ps = Connect.prepareStatement(query);
        ps.setString(1, "%" + Search + "%");
        ps.setString(2, "%" + Search + "%");
        ps.setString(3, "%" + Search + "%");
        ps.setString(4, "%" + Search + "%");
        ps.setString(5, "%" + Search + "%");
        ResultSet rs = ps.executeQuery();

        try {
            while (rs.next()) {
                String id, fullname, position, fname, mname, lname;
                id = rs.getString("Employee_Id");
                fname = rs.getString("First_Name");
                mname = rs.getString("Middle_Name");
                lname = rs.getString("Last_Name");
                position = rs.getString("Designation");
                EmployeeList.add(new Employee(id, fname, mname, lname, position));
                employeeTable.setItems(EmployeeList);
            }
            emp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            position.setCellValueFactory(new PropertyValueFactory<>("Position"));
            fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            mname.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));
            lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        } catch (Exception e) {
            System.out.println(e);
            ps.close();
            rs.close();
        } finally {
            ps.close();
            rs.close();
        }
    }

//    On process
    public String Id;

    @FXML
    public void GetDataOnClick(MouseEvent event){
        Employee person = employeeTable.getSelectionModel().getSelectedItem();
        try{
            Id = person.getId();
        }catch(Exception e){
            Id = null;
        }
        
    }

    @FXML
    private void ViewBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Employee_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("First_Name");
                String middleName = rs.getString("Middle_name");
                String lastName = rs.getString("Last_Name");
                String position = rs.getString("Designation");
                String status = rs.getString("Status");
                int access = rs.getInt("Access");
                String dob = rs.getString("DOB");
                String hired = rs.getString("Date_Hired");
                String resigned = rs.getString("Date_Resigned");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
                Parent root = loader.load();
                ViewController ViewController = loader.getController();
                ViewController.display(firstName, middleName, lastName, Id, dob, access, position, status, hired, resigned);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                action.Draggable(stage, root);
                stage.show();

            } else {
                ps.close();
                rs.close();
            }
        } catch (Exception e) {
            ps.close();
            rs.close();
        } finally {
            ps.close();
            rs.close();
        }
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Employee WHERE Employee_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("First_Name");
                String middleName = rs.getString("Middle_name");
                String lastName = rs.getString("Last_Name");
                String position = rs.getString("Designation");
                String status = rs.getString("Status");
                int access = rs.getInt("Access");
                String dob = rs.getString("DOB");
                String hired = rs.getString("Date_Hired");
                String resigned = rs.getString("Date_Resigned");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditEmployee.fxml"));
                Parent root = loader.load();
                EditEmployeeController EditEmployeeController = loader.getController();
                EditEmployeeController.display(firstName, middleName, lastName, Id, dob, access, position, status, hired, resigned);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                action.Draggable(stage, root);
                stage.show();

            } else {
                ps.close();
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            ps.close();
            rs.close();
        } finally {
            ps.close();
            rs.close();
        }
    }

}
