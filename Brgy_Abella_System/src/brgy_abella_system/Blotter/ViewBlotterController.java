package brgy_abella_system.Blotter;

import brgy_abella_system.Repeatables;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ViewBlotterController implements Initializable {
    
    Repeatables action = new Repeatables();
    Connection Connect;
    
    @FXML
    private Button cancelBtn;
    @FXML
    private Label D_First_Name;
    @FXML
    private Label D_Middle_Name;
    @FXML
    private Label D_Last_Name;
    @FXML
    private Label D_Contact_No;
    @FXML
    private Label D_Street;
    @FXML
    private Label D_Barangay;
    @FXML
    private Label D_City;
    @FXML
    private Label D_Province;
    @FXML
    private Label CaseNumber;
    @FXML
    private Label EmployeeID;
    @FXML
    private Label ComplainantID;
    @FXML
    private Label Complaint_Date;
    @FXML
    private TextArea Complaint;
    @FXML
    private TextArea Solution;
    @FXML
    private Label C_First_Name;
    @FXML
    private Label C_Middle_Name;
    @FXML
    private Label C_Last_Name;
    @FXML
    private Label C_Contact_No;
    @FXML
    private Label C_Street;
    @FXML
    private Label C_Barangay;
    @FXML
    private Label C_City;
    @FXML
    private Label C_Province;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Complaint.setWrapText(true);
        Solution.setWrapText(true);
    }    

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
    public void display(String case_Number, String complaint_Date, String complaint, String solution, String employee_ID, 
                        String complainant_ID_C, String first_Name_C, String middle_Name_C, String last_Name_C, String contact_Number_C, String street_C, String barangay_C, String city_C, String province_C, 
                        String first_Name_D, String middle_Name_D, String last_Name_D, String contact_Number_D, String street_D, String barangay_D, String city_D, String province_D) {
        CaseNumber.setText(case_Number);
        EmployeeID.setText(employee_ID);
        Complaint_Date.setText(complaint_Date);
        Complaint.setText(complaint);
        Solution.setText(solution);
        
        ComplainantID.setText(complainant_ID_C);
        C_First_Name.setText(first_Name_C);
        C_Middle_Name.setText(middle_Name_C);
        C_Last_Name.setText(last_Name_C);
        C_Contact_No.setText(contact_Number_C);
        C_Street.setText(street_C);
        C_Barangay.setText(barangay_C);
        C_City.setText(city_C);
        C_Province.setText(province_C);
        
        D_First_Name.setText(first_Name_D);
        D_Middle_Name.setText(middle_Name_D);
        D_Last_Name.setText(last_Name_D);
        D_Contact_No.setText(contact_Number_D);
        D_Street.setText(street_D);
        D_Barangay.setText(barangay_D);
        D_City.setText(city_D);
        D_Province.setText(province_D);
    }
    
}
