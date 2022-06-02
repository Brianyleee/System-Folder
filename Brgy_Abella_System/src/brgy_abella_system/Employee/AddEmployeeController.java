/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Employee;

import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class AddEmployeeController implements Initializable {

    Repeatables action = new Repeatables();
    Functions AddEmployeeModel = new Functions();
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
        int Access;
        if (Emp_Access.getValue() == null) {
            Access = 3;
        } else {
            Access = Emp_Access.getValue();
        }

        if (!fname.isEmpty() || !lname.isEmpty() || !id.isEmpty() || DOB != null || !Designation.isEmpty() || Hired != null || !Status.isEmpty() || Access != 3) {
            if (!AddEmployeeModel.isEmpIdEsxisting(id)) {
                if (AddEmployeeModel.InsertEmployee(id, fname, mname, lname, Designation, DOB, Hired, Resign, Status, Access)) {
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
}
