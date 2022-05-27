package brgy_abella_system.Blotter;

import brgy_abella_system.Functions;
import brgy_abella_system.Repeatables;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditBlotterController implements Initializable {

    Repeatables action = new Repeatables();
    Functions EditBlotterModel = new Functions();
   
    @FXML
    private TextField Blotter_Complainant_ID_C;
    @FXML
    private TextField Blotter_First_Name_C;
    @FXML
    private TextField Blotter_Middle_Name_C;
    @FXML
    private TextField Blotter_Last_Name_C;
    @FXML
    private TextField Blotter_Contact_No_C;
    @FXML
    private TextField Blotter_Street_C;
    @FXML
    private TextField Blotter_Barangay_C;
    @FXML
    private TextField Blotter_City_C;
    @FXML
    private TextField Blotter_Province_C;
    @FXML
    private TextField Blotter_Case_No;
    @FXML
    private TextField Blotter_Employee_ID;
    @FXML
    private DatePicker Blotter_Complaint_Date;
    @FXML
    private TextArea Blotter_Complaint;
    @FXML
    private TextArea Blotter_Solution;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Alert;
    @FXML
    private TextField Blotter_First_Name_D;
    @FXML
    private TextField Blotter_Middle_Name_D;
    @FXML
    private TextField Blotter_Last_Name_D;
    @FXML
    private TextField Blotter_Contact_No_D;
    @FXML
    private TextField Blotter_Street_D;
    @FXML
    private TextField Blotter_Barangay_D;
    @FXML
    private TextField Blotter_City_D;
    @FXML
    private TextField Blotter_Province_D;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Blotter_Complaint.setWrapText(true);
        Blotter_Solution.setWrapText(true);
    }    

    @FXML
    private void SaveButtonAction(ActionEvent event) throws SQLException {
        String case_Number = Blotter_Case_No.getText().toUpperCase();
        LocalDate complaint_Date = Blotter_Complaint_Date.getValue();
        String complaint = Blotter_Complaint.getText();
        String solution = Blotter_Solution.getText();
        String employee_ID = Blotter_Employee_ID.getText().toUpperCase();
                
        String complainant_ID_C = Blotter_Complainant_ID_C.getText().toUpperCase();
        String first_Name_C = Blotter_First_Name_C.getText().toUpperCase();
        String middle_Name_C = Blotter_Middle_Name_C.getText().toUpperCase();
        String last_Name_C = Blotter_Last_Name_C.getText().toUpperCase();
        String contact_Number_C = Blotter_Contact_No_C.getText().toUpperCase();
        String street_C = Blotter_Street_C.getText().toUpperCase();
        String barangay_C = Blotter_Barangay_C.getText().toUpperCase();
        String city_C = Blotter_City_C.getText().toUpperCase();
        String province_C = Blotter_Province_C.getText().toUpperCase();
                
        String first_Name_D = Blotter_First_Name_D.getText().toUpperCase();
        String middle_Name_D = Blotter_Middle_Name_D.getText().toUpperCase();
        String last_Name_D = Blotter_Last_Name_D.getText().toUpperCase();
        String contact_Number_D = Blotter_Contact_No_D.getText().toUpperCase();
        String street_D = Blotter_Street_D.getText().toUpperCase();
        String barangay_D = Blotter_Barangay_D.getText().toUpperCase();
        String city_D = Blotter_City_D.getText().toUpperCase();
        String province_D = Blotter_Province_D.getText().toUpperCase();
        
        if (!case_Number.isEmpty() && !complaint.isEmpty() && !solution.isEmpty() && !employee_ID.isEmpty() && complaint_Date != null
            && !complainant_ID_C.isEmpty() && !first_Name_C.isEmpty() && !middle_Name_C.isEmpty() && !last_Name_C.isEmpty() && !contact_Number_C.isEmpty() && !street_C.isEmpty() && !barangay_C.isEmpty() && !city_C.isEmpty() && !province_C.isEmpty()
            && !first_Name_D.isEmpty() && !middle_Name_D.isEmpty() && !last_Name_D.isEmpty() && !contact_Number_D.isEmpty() && !street_D.isEmpty() && !barangay_D.isEmpty() && !city_D.isEmpty() && !province_D.isEmpty()) {
            if (EditBlotterModel.UpdateBlotterFile(case_Number, employee_ID, complaint_Date, complaint, solution, 
                            complainant_ID_C, first_Name_C, middle_Name_C, last_Name_C, contact_Number_C, street_C, barangay_C, city_C, province_C,
                            first_Name_D, middle_Name_D, last_Name_D, contact_Number_D, street_D, barangay_D, city_D, province_D)) {
                            Alert.setText("Successfully Edited the Blotter");
                            action.Exit(saveBtn);
                    } else {
                        Alert.setText("Editing of Blotter Failed");
                    }
        } else {
            Alert.setText("Fill out the required information (*)");
        }
    }

    public void display(String case_Number, String complaint_Date, String complaint, String solution, String employee_ID, 
                        String complainant_ID_C, String first_Name_C, String middle_Name_C, String last_Name_C, String contact_Number_C, String street_C, String barangay_C, String city_C, String province_C, 
                        String first_Name_D, String middle_Name_D, String last_Name_D, String contact_Number_D, String street_D, String barangay_D, String city_D, String province_D) {
        Blotter_Case_No.setText(case_Number);
        Blotter_Employee_ID.setText(employee_ID);
        Blotter_Complaint_Date.setValue(LocalDate.parse(complaint_Date));
        Blotter_Complaint.setText(complaint);
        Blotter_Solution.setText(solution);
        
        Blotter_Complainant_ID_C.setText(complainant_ID_C);
        Blotter_First_Name_C.setText(first_Name_C);
        Blotter_Middle_Name_C.setText(middle_Name_C);
        Blotter_Last_Name_C.setText(last_Name_C);
        Blotter_Contact_No_C.setText(contact_Number_C);
        Blotter_Street_C.setText(street_C);
        Blotter_Barangay_C.setText(barangay_C);
        Blotter_City_C.setText(city_C);
        Blotter_Province_C.setText(province_C);
        
        Blotter_First_Name_D.setText(first_Name_D);
        Blotter_Middle_Name_D.setText(middle_Name_D);
        Blotter_Last_Name_D.setText(last_Name_D);
        Blotter_Contact_No_D.setText(contact_Number_D);
        Blotter_Street_D.setText(street_D);
        Blotter_Barangay_D.setText(barangay_D);
        Blotter_City_D.setText(city_D);
        Blotter_Province_D.setText(province_D);
    }
    
    @FXML
    private void ExitButtonAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
}
