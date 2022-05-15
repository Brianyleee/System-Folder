/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Employee;

import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;
import java.net.URL;
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
public class EditEmployeeController implements Initializable {

    Repeatables action = new Repeatables();
    Functions EditModel = new Functions();
    ObservableList<String> StatusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<Integer> Access = FXCollections.observableArrayList(0, 1);
    @FXML
    private TextField Emp_id;
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
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Emp_Status.setItems(StatusList);
        Emp_Access.setItems(Access);
    }

    @FXML
    private void SaveButtonAction(ActionEvent event) throws SQLException{
        String fname = Emp_First_Name.getText().toUpperCase();
        String mname = Emp_Middle_Name.getText().toUpperCase();
        String lname = Emp_Last_Name.getText().toUpperCase();
        String id = Emp_id.getText().toUpperCase();
        LocalDate DOB = Emp_Birth_Date.getValue();
        String Designation = Emp_Designation.getText().toUpperCase();
        LocalDate Hired = Emp_Date_Hired.getValue();
        LocalDate Resign = Emp_Date_Resigned.getValue();
        int Access = Emp_Access.getValue();
        String Status = Emp_Status.getValue().toUpperCase();
        if (!fname.isEmpty() && !lname.isEmpty() && !id.isEmpty() && DOB != null && !Designation.isEmpty() && Hired != null) {
                if (EditModel.UpdateEmployeeInfo(id, Access, Designation, fname, mname, lname, DOB, Hired, Resign, Status)) {
                    Alert.setText("Employee Added");
                    action.Exit(saveBtn);
                } else {
                    Alert.setText("Employee not Added");
                }
        } else {
            Alert.setText("Fill out the Necessary Information (*)");
        }
    }

    @FXML
    private void ExitButtonAction(ActionEvent event) {
        action.Exit(saveBtn);
    }

    public void display(String fname, String mname, String lname, String id, String DB, int acc, String Designation, String Status, String emp_hired, String emp_resigned) {

        Emp_First_Name.setText(fname);
        Emp_Middle_Name.setText(mname);
        Emp_Last_Name.setText(lname);
        Emp_id.setText(id);
        Emp_Status.setValue(Status);
        Emp_Designation.setText(Designation);
        Emp_Access.setValue(acc);
        Emp_Birth_Date.setValue(LocalDate.parse(DB));
        Emp_Date_Hired.setValue(LocalDate.parse(emp_hired));
        if (emp_resigned != null) {
            Emp_Date_Resigned.setValue(LocalDate.parse(emp_resigned));
        }
    }

}
