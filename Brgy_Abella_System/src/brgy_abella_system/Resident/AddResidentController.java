package brgy_abella_system.Resident;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

import java.net.URL;
import java.io.*;
import java.sql.*;
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

public class AddResidentController implements Initializable {

    Repeatables action = new Repeatables();
    ObservableList<String> ResidentGender = FXCollections.observableArrayList("MALE", "FEMALE");
    ObservableList<String> ResidentMaritalStatus = FXCollections.observableArrayList("SINGLE", "MARRIED", "WIDOWED", "DIVORCE/SEPARATED", "LIVE-IN/COMMON-LAW");
    ObservableList<String> ResidentZone = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7");
    Connection Connect;
    
    public AddResidentController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    @FXML
    private TextField Add_Resident_Id;
    @FXML
    private TextField Add_Employee_Id;
    @FXML
    private TextField Add_First_Name;
    @FXML
    private TextField Add_Middle_Name;
    @FXML
    private TextField Add_Last_Name;
    @FXML
    private ComboBox<String> Add_Gender;
    @FXML
    private DatePicker Add_Birth_Date;
    @FXML
    private ComboBox<String> Add_Marital_Status;
    @FXML
    private TextField Add_Contact_No;
    @FXML
    private ImageView Add_Resident_Image;
    @FXML
    private TextField Add_House_No;
    @FXML
    private TextField Add_Street;
    @FXML
    private ComboBox<String> Add_Zone;
    @FXML
    private TextField Add_Barangay;
    @FXML
    private TextField Add_City;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Alert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Add_Gender.setItems(ResidentGender);
        Add_Marital_Status.setItems(ResidentMaritalStatus);
        Add_Zone.setItems(ResidentZone);
    }    

    @FXML
    private void SaveButtonAction(ActionEvent event) throws SQLException {
        
        String residentImage;
        
        String residentID = Add_Resident_Id.getText().toUpperCase();
        String employeeID = Add_Employee_Id.getText().toUpperCase();
        
        String firstName = Add_First_Name.getText().toUpperCase();
        String middleName = Add_Middle_Name.getText().toUpperCase();
        String lastName = Add_Last_Name.getText().toUpperCase();
        
        String gender = Add_Gender.getValue();
        String birthDate = Add_Birth_Date.getValue().toString();
        String maritalStatus = Add_Marital_Status.getValue();
        String contactNo = Add_Contact_No.getText();
        
        String houseNo = Add_House_No.getText().toUpperCase();;
        String street = Add_Street.getText().toUpperCase();
        String zone = Add_Zone.getValue();
        String barangay = Add_Barangay.getText().toUpperCase();
        String city = Add_City.getText().toUpperCase();
        
        if (!residentID.isEmpty() && !employeeID.isEmpty() && !firstName.isEmpty() && !middleName.isEmpty() && !lastName.isEmpty() && gender != null
                && birthDate != null && maritalStatus != null && !contactNo.isEmpty() && !houseNo.isEmpty() && !street.isEmpty()
                && zone != null && !barangay.isEmpty() && !city.isEmpty()) {
            residentImage = SaveImage();
            StoreData(residentImage, residentID, employeeID, firstName, middleName, lastName,
            gender, birthDate, maritalStatus, contactNo, houseNo,
            street, zone, barangay, city);
            Alert.setText("Successfully added the resident");
            action.Exit(saveBtn);
        } else {
            Alert.setText("Please fill out the required information properly");
        }
    }
    
    public void StoreData(String residentImage, String residentID, String employeeID, String firstName, String middleName, String lastName,
            String gender, String birthDate, String maritalStatus, String contactNo, String houseNo,
            String street, String zone, String barangay, String city) throws SQLException {
        PreparedStatement ps = null;
        String query = "INSERT INTO Resident (Resident_Id, Employee_Id, First_Name, Middle_Name, Last_Name, House_No, Street, Zone, "
                + "Barangay, City, Gender, Marital_Status, DOB, Image, Contact_No) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, residentID);
            ps.setString(2, employeeID);
            ps.setString(3, firstName);
            ps.setString(4, middleName);
            ps.setString(5, lastName);
            ps.setString(6, houseNo);
            ps.setString(7, street);
            ps.setString(8, zone);
            ps.setString(9, barangay);
            ps.setString(10, city);
            ps.setString(11, gender);
            ps.setString(12, maritalStatus);
            ps.setString(13, birthDate);
            ps.setString(14, residentImage);
            ps.setString(15, contactNo);
            ps.executeUpdate();
            action.Exit(saveBtn);
        } catch (Exception e) {
            e.printStackTrace();
            ps.close();
        } finally {
            ps.close();
        }
    }

    public String SaveImage() {
        Image ResidentImage = Add_Resident_Image.getImage();
        String ImageName = Add_Last_Name.getText() + ", " + Add_First_Name.getText() + Add_Middle_Name.getText().charAt(0) + ".png";
        File file = new File("src/brg_abella_system/Resident/Images/" + ImageName);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(ResidentImage, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "src/brg_abella_system/Resident/Images/" + ImageName;
    }
    
    @FXML
    private void ExitButtonAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
    @FXML
    private void handleDropImage(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        Add_Resident_Image.setImage(img);
    }
    
    @FXML
    private void handleDragOverImage(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
}
