/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.Employee;

import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;
import java.net.URL;
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
    private void SaveButtonAction(ActionEvent event) {
        action.Exit(saveBtn);
    }

    @FXML
    private void ExitButtonAction(ActionEvent event) {
    }

    public void display(String fname, String mname, String lname, String id, String DB, int acc, String Designation, String Status, String emp_hired, String emp_resigned) {

        Emp_First_Name.setText(lname);
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
