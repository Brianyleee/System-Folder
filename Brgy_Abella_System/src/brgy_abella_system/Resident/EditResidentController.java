package brgy_abella_system.Resident;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

import java.net.URL;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javax.imageio.ImageIO;
import javafx.scene.image.ImageView;

public class EditResidentController implements Initializable {

    Repeatables action = new Repeatables();
    ObservableList<String> ResidentGender = FXCollections.observableArrayList("MALE", "FEMALE");
    ObservableList<String> ResidentMaritalStatus = FXCollections.observableArrayList("SINGLE", "MARRIED", "WIDOWED", "DIVORCE/SEPARATED", "LIVE-IN/COMMON-LAW");
    ObservableList<String> ResidentZone = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7");
    Connection Connect;
    
    public EditResidentController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    
    @FXML
    private TextField Edit_Resident_Id;
    @FXML
    private TextField Edit_Employee_Id;
    @FXML
    private TextField Edit_First_Name;
    @FXML
    private TextField Edit_Middle_Name;
    @FXML
    private TextField Edit_Last_Name;
    @FXML
    private ComboBox<String> Edit_Gender;
    @FXML
    private DatePicker Edit_Birth_Date;
    @FXML
    private ComboBox<String> Edit_Marital_Status;
    @FXML
    private ImageView Edit_Resident_Image;
    @FXML
    private TextField Edit_Contact_No;
    @FXML
    private TextField Edit_House_No;
    @FXML
    private TextField Edit_Street;
    @FXML
    private ComboBox<String> Edit_Zone;
    @FXML
    private TextField Edit_Barangay;
    @FXML
    private TextField Edit_City;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Alert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Edit_Gender.setItems(ResidentGender);
        Edit_Marital_Status.setItems(ResidentMaritalStatus);
        Edit_Zone.setItems(ResidentZone);
    }    

    @FXML
    private void SaveButtonAction(ActionEvent event) throws SQLException{
        
        String residentImage;
        
        String residentID = Edit_Resident_Id.getText().toUpperCase();
        String employeeID = Edit_Employee_Id.getText().toUpperCase();
        
        String firstName = Edit_First_Name.getText().toUpperCase();
        String middleName = Edit_Middle_Name.getText().toUpperCase();
        String lastName = Edit_Last_Name.getText().toUpperCase();
        
        String gender = Edit_Gender.getValue();
        String birthDate = Edit_Birth_Date.getValue().toString();
        String maritalStatus = Edit_Marital_Status.getValue();
        String contactNo = Edit_Contact_No.getText();
        
        String houseNo = Edit_House_No.getText().toUpperCase();
        String street = Edit_Street.getText().toUpperCase();
        String zone = Edit_Zone.getValue();
        String barangay = Edit_Barangay.getText().toUpperCase();
        String city = Edit_City.getText().toUpperCase();
        
        if (!residentID.isEmpty() && !employeeID.isEmpty() && !firstName.isEmpty() && !middleName.isEmpty() && !lastName.isEmpty() && gender != null
                && birthDate != null && maritalStatus != null && !contactNo.isEmpty() && !houseNo.isEmpty() && !street.isEmpty()
                && zone != null && !barangay.isEmpty() && !city.isEmpty()) {
            residentImage = SaveImage();
            StoreData(residentImage, residentID, employeeID, firstName, middleName, lastName,
                gender, birthDate, maritalStatus, contactNo, houseNo,
                street, zone, barangay, city);
            Alert.setText("Successfully edited the resident's information");
            action.Exit(saveBtn);
        } else {
            Alert.setText("Please fill out the required information properly");
        }
        
    }

    public void StoreData(String residentImage, String residentID, String employeeID, String firstName, String middleName, String lastName,
            String gender, String birthDate, String maritalStatus, String contactNo, String houseNo,
            String street, String zone, String barangay, String city) throws SQLException {
        PreparedStatement ps = null;
        String query = "UPDATE Resident SET "
                + "Employee_Id = ?,"
                + "First_Name = ?," + "Middle_Name = ?," + "Last_Name = ?,"
                + "House_No = ?,"
                + "Street = ?,"
                + "Zone = ?,"
                + "Barangay = ?,"
                + "City = ?,"
                + "Gender = ?,"
                + "Marital_Status = ?,"
                + "DOB = ?,"
                + "Image = ?,"
                + "Contact_No = ?"
                + " WHERE Resident_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, employeeID);
            ps.setString(2, firstName);
            ps.setString(3, middleName);
            ps.setString(4, lastName);
            ps.setString(5, houseNo);
            ps.setString(6, street);
            ps.setString(7, zone);
            ps.setString(8, barangay);
            ps.setString(9, city);
            ps.setString(10, gender);
            ps.setString(11, maritalStatus);
            ps.setString(12, birthDate);
            ps.setString(13, residentImage);
            ps.setString(14, contactNo);
            ps.setString(15, residentID);
            ps.executeUpdate();
            action.Exit(saveBtn);
        } catch (Exception e) {
            e.printStackTrace();
            ps.close();
        } finally {
            ps.close();
        }
    }
    
    public void Display(String residentID) throws SQLException {
        Edit_Resident_Id.setText(residentID);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Resident WHERE Resident_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, residentID);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String employeeID = rs.getString("Employee_Id");
                
                String first_Name = rs.getString("First_Name");
                String middle_Name = rs.getString("Middle_Name");
                String last_Name = rs.getString("Last_Name");
                
                String gender = rs.getString("Gender");
                String birthDate = rs.getString("DOB");
                String marital_Status = rs.getString("Marital_Status");
                String contact_Number = rs.getString("Contact_No");
                
                String house_Number = rs.getString("House_No");
                String street = rs.getString("Street");
                String zone = rs.getString("Zone");
                String barangay = rs.getString("Barangay");
                String city = rs.getString("City");
                
                String resident_Image = rs.getString("Image");
                
                Image view_Image = new Image(new File(resident_Image).toURI().toString());
                Edit_Resident_Image.setImage(view_Image);
                
                Edit_Employee_Id.setText(employeeID);
                
                Edit_First_Name.setText(first_Name);
                Edit_Middle_Name.setText(middle_Name);
                Edit_Last_Name.setText(last_Name);
                
                Edit_Gender.setValue(gender);
                Edit_Birth_Date.setValue(LocalDate.parse(birthDate));
                Edit_Marital_Status.setValue(marital_Status);
                Edit_Contact_No.setText(contact_Number);
                
                Edit_House_No.setText(house_Number);
                Edit_Street.setText(street);
                Edit_Zone.setValue(zone);
                Edit_Barangay.setText(barangay);
                Edit_City.setText(city);
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
    private void ExitButtonAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
    public String SaveImage() {
        Image ResidentImage = Edit_Resident_Image.getImage();
        String ImageName = Edit_Last_Name.getText() + ", " + Edit_First_Name.getText() + Edit_Middle_Name.getText().charAt(0) + ".png";
        File file = new File("src/brg_abella_system/Resident/Images/" + ImageName);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(ResidentImage, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "src/brg_abella_system/Resident/Images/" + ImageName;
    }
    
    @FXML
    private void handleDropImage(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        Edit_Resident_Image.setImage(img);
    }
    
    @FXML
    private void handleDragOverImage(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
}
