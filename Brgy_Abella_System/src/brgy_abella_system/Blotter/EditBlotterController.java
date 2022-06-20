package brgy_abella_system.Blotter;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    Connection Connect;

    public EditBlotterController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
   
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
        String contact_Number_C;
        boolean isDigitC = false;
        if (Blotter_Contact_No_C.getText() == null) {
            contact_Number_C = null;
        } else {
            for (char c : Blotter_Contact_No_C.getText().toCharArray()) {
                if (!Character.isDigit(c)) {
                    isDigitC = false;
                } else {
                    isDigitC = true;
                }
            }
            if (isDigitC == false) {
                contact_Number_C = null;
            } else {
                contact_Number_C = Blotter_Contact_No_C.getText().toUpperCase();
            }
        }
        String street_C = Blotter_Street_C.getText().toUpperCase();
        String barangay_C = Blotter_Barangay_C.getText().toUpperCase();
        String city_C = Blotter_City_C.getText().toUpperCase();
        String province_C = Blotter_Province_C.getText().toUpperCase();
                
        String first_Name_D = Blotter_First_Name_D.getText().toUpperCase();
        String middle_Name_D = Blotter_Middle_Name_D.getText().toUpperCase();
        String last_Name_D = Blotter_Last_Name_D.getText().toUpperCase();
        String contact_Number_D;
        boolean isDigitD = false;
        if (Blotter_Contact_No_D.getText() == null) {
            contact_Number_D = null;
        } else {
            for (char c : Blotter_Contact_No_D.getText().toCharArray()) {
                if (!Character.isDigit(c)) {
                    isDigitD = false;
                } else {
                    isDigitD = true;
                }
            }
            if (isDigitD == false) {
                contact_Number_D = null;
            } else {
                contact_Number_D = Blotter_Contact_No_D.getText().toUpperCase();
            }
        }
        String street_D = Blotter_Street_D.getText().toUpperCase();
        String barangay_D = Blotter_Barangay_D.getText().toUpperCase();
        String city_D = Blotter_City_D.getText().toUpperCase();
        String province_D = Blotter_Province_D.getText().toUpperCase();
        
        if (!case_Number.isEmpty() && !complaint.isEmpty() && !solution.isEmpty() && !employee_ID.isEmpty() && complaint_Date != null
            && !complainant_ID_C.isEmpty() && !first_Name_C.isEmpty() && !middle_Name_C.isEmpty() && !last_Name_C.isEmpty() && contact_Number_C != null && !street_C.isEmpty() && !barangay_C.isEmpty() && !city_C.isEmpty() && !province_C.isEmpty()
            && !first_Name_D.isEmpty() && !middle_Name_D.isEmpty() && !last_Name_D.isEmpty() && contact_Number_D != null && !street_D.isEmpty() && !barangay_D.isEmpty() && !city_D.isEmpty() && !province_D.isEmpty()) {
            if (UpdateBlotterFile(case_Number, employee_ID, complaint_Date, complaint, solution, 
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
    
    //    Update Blotter File in the Database Table BLOTTER    
    public boolean UpdateBlotterFile(String case_Number, String employee_ID, LocalDate complaint_Date, String complaint, String solution,
                               String complainant_ID_C, String first_Name_C, String middle_Name_C, String last_Name_C, String contact_Number_C, String street_C, String barangay_C, String city_C, String province_C,
                               String first_Name_D, String middle_Name_D, String last_Name_D, String contact_Number_D, String street_D, String barangay_D, String city_D, String province_D) throws SQLException{
        PreparedStatement ps = null;
        String query = "UPDATE Blotter SET "
                + "Complaint_Date=?,"
                + "Complaint=?,"
                + "Solution=?,"
                + "First_Name_C=?,"
                + "Middle_Name_C=?,"
                + "Last_Name_C=?,"
                + "Contact_No_C=?,"
                + "Street_C=?,"
                + "Barangay_C=?,"
                + "City_C=?,"
                + "Province_C=?,"
                + "First_Name_D=?,"
                + "Middle_Name_D=?,"
                + "Last_Name_D=?,"
                + "Contact_No_D=?,"
                + "Street_D=?,"
                + "Barangay_D=?,"
                + "City_D=?,"
                + "Province_D=?"
                + " WHERE Case_No=? AND Employee_Id=? AND Complainant_Id=?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, complaint_Date.toString());
            ps.setString(2, complaint);
            ps.setString(3, solution);
            ps.setString(4, first_Name_C);
            ps.setString(5, middle_Name_C);
            ps.setString(6, last_Name_C);
            ps.setString(7, contact_Number_C);
            ps.setString(8, street_C);
            ps.setString(9, barangay_C);
            ps.setString(10, city_C);
            ps.setString(11, province_C);
            ps.setString(12, first_Name_D);
            ps.setString(13, middle_Name_D);
            ps.setString(14, last_Name_D);
            ps.setString(15, contact_Number_D);
            ps.setString(16, street_D);
            ps.setString(17, barangay_D);
            ps.setString(18, city_D);
            ps.setString(19, province_D);
            ps.setString(20, case_Number);
            ps.setString(21, employee_ID);
            ps.setString(22, complainant_ID_C);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
        }
    }
    
}
