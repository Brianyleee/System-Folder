package brgy_abella_system.Resident;

import brgy_abella_system.Repeatables;

import java.net.URL;
import java.io.*;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ViewResidentController implements Initializable {

    Repeatables action = new Repeatables();
    Connection Connect;
    
    @FXML
    private ImageView View_Resident_Image;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button printIndigency;
    @FXML
    private Label View_First_Name;
    @FXML
    private Label View_Middle_Name;
    @FXML
    private Label View_Last_Name;
    @FXML
    private Label View_Contact_No;
    @FXML
    private Label View_Gender;
    @FXML
    private Label View_Birth_Date;
    @FXML
    private Label EmployeeID;
    @FXML
    private Label ResidentID;
    @FXML
    private Label View_Marital_Status;
    @FXML
    private Label View_House_No;
    @FXML
    private Label View_Street;
    @FXML
    private Label View_Zone;
    @FXML
    private Label View_Barangay;
    @FXML
    private Label View_City;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
    public void Display(String residentImage, String residentID, String employeeID, String firstName, String middleName, String lastName,
            String gender, String birthDate, String maritalStatus, String contactNo, String houseNo,
            String street, String zone, String barangay, String city) {
        try {
            Image viewImage = new Image(new File(residentImage).toURI().toString());
            View_Resident_Image.setImage(viewImage);
            
            EmployeeID.setText(employeeID);
            ResidentID.setText(residentID);
            
            View_First_Name.setText(firstName);
            View_Middle_Name.setText(middleName);
            View_Last_Name.setText(lastName);
            
            View_Contact_No.setText(contactNo);
            View_Gender.setText(gender);
            View_Birth_Date.setText(birthDate);
            View_Marital_Status.setText(maritalStatus);
            
            View_House_No.setText(houseNo);
            View_Street.setText(street);
            View_Zone.setText(zone);
            View_Barangay.setText(barangay);
            View_City.setText(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
