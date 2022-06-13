package brgy_abella_system.FinancialAid;

/**
 *
 * @author smile
 */

import brgy_abella_system.Functions;
import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javax.imageio.ImageIO;

public class AddRecipientController implements Initializable {
    
    ObservableList<String> StatusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<String> Sex = FXCollections.observableArrayList("Male", "Female");
    ObservableList<Integer> Zone = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    ObservableList<String> Level = FXCollections.observableArrayList("1st Year", "2nd Year", "3rd Year", "4th Year");
    ObservableList<String> Income = FXCollections.observableArrayList("Below 5,000", "5,000-10,000", "10,000-15,000", "Above 15,000");
    
    Repeatables action = new Repeatables();
    Functions AddRecipientModel = new Functions();
    
    Connection Connect;
    public AddRecipientController() { 
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    
    @FXML
    private ImageView Profile;
    @FXML
    private Label Alert;
    @FXML
    private TextField Id;
    @FXML
    private ImageView grades;
    @FXML
    private ImageView matri;
    @FXML
    private ComboBox<String> status;
    @FXML
    private ComboBox<Integer> zone;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private DatePicker DOB;
    @FXML
    private DatePicker DayApplied;
    @FXML
    private ComboBox<String> CollegeLevel;
    @FXML
    private ComboBox<String> AnnInc;
    @FXML
    private TextField fname;
    @FXML
    private TextField mname;
    @FXML
    private TextField lname;
    @FXML
    private TextField school;
    @FXML
    private TextField course;
    @FXML
    private TextField address;
    @FXML
    private TextField age;
    @FXML
    private TextField POB;
    @FXML
    private TextField PhoneNumR;
    @FXML
    private TextField ZoneOrg;
    @FXML
    private TextField fnameM;
    @FXML
    private TextField fnameF;
    @FXML
    private TextField mnameM;
    @FXML
    private TextField mnameF;
    @FXML
    private TextField lnameM;
    @FXML
    private TextField lnameF;
    @FXML
    private TextField PhoneNumM;
    @FXML
    private TextField PhoneNumF;
    @FXML
    private TextField occupationM;
    @FXML
    private TextField occupationF;
    @FXML
    private TextField AvgInc;
    @FXML
    private TextField EnrollPaid;
    @FXML
    private TextField SemEsti;
    @FXML
    private Button SaveBtn;
    @FXML
    private Button ExitBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        status.setItems(StatusList);
        zone.setItems(Zone);
        sex.setItems(Sex);
        CollegeLevel.setItems(Level);
        AnnInc.setItems(Income);
    }

    @FXML
    private void SaveBtnAction(ActionEvent event) throws SQLException {
        
        String profile_Picture;
        String previous_Grades;
        String matriculation;
        
        String recipient_ID = Id.getText();
        String first_Name = fname.getText();
        String middle_Name = mname.getText();
        String last_Name = lname.getText();
        String CourseR = course.getText();
        String SchoolAttend = school.getText();
        String RStatus = status.getValue();
        String RAddress = address.getText();
        int RZone;
        if (zone.getValue() == null) {
            RZone = 0;
        } else {
            RZone = zone.getValue();
        }
        String RSex = sex.getValue();
        String DateOB;
        if (DOB.getValue() == null) {
            DateOB = null;
        } else {
            DateOB = DOB.getValue().toString();
        }
        String PlaceOB = POB.getText();
        String Contact_Number = PhoneNumR.getText();
        String Zone_Org = ZoneOrg.getText();
        String Day_Applied;
        if (DayApplied.getValue() == null) {
            Day_Applied = null;
        } else {
            Day_Applied = DayApplied.getValue().toString();
        }
        String College_Level = CollegeLevel.getValue();

        String MotherlName = lnameM.getText();
        String MotherfName = fnameM.getText();
        String MothermName = mname.getText();

        String FatherlName = lnameF.getText();
        String FatherfName = fnameF.getText();
        String FathermName = mnameF.getText();

        String ConNum_M = PhoneNumM.getText();
        String ConNum_F = PhoneNumF.getText();
        String OccupationM = occupationM.getText();
        String OccupationF = occupationF.getText();
        String EstAnnInc = AnnInc.getValue();
        String AvgMonInc = AvgInc.getText();
        String Enroll_Paid = EnrollPaid.getText();
        String SemEstimate = SemEsti.getText();
        int Age = 0;
        boolean isDigit = false;
        if (age.getText() == null) {
            Age = 0;
        } else {
            for (char c : age.getText().toCharArray()) {
                if (!Character.isDigit(c)) {
                    isDigit = false;
                } else {
                    isDigit = true;
                }
            }
            if (isDigit == false) {
                Age = 0;
            } else {
                Age = Integer.valueOf(age.getText());
            }
        }

        if (!first_Name.isEmpty() && College_Level != null && !last_Name.isEmpty() && !CourseR.isEmpty() && !SchoolAttend.isEmpty() && Day_Applied != null && Age != 0
            && !recipient_ID.isEmpty() && EstAnnInc != null && !middle_Name.isEmpty() && RStatus != null && !RAddress.isEmpty() && RSex != null && DateOB != null && !PlaceOB.isEmpty()) {
            if (!AddRecipientModel.isRecipientIDExisting(recipient_ID)) {
                profile_Picture = SaveProfile();
                previous_Grades = SaveGrades();
                matriculation = SaveMatri();
                StoreData(profile_Picture, previous_Grades, matriculation, first_Name, middle_Name, last_Name,
                    CourseR, SchoolAttend, RStatus, RAddress, RZone, RSex, DateOB,
                    PlaceOB, Contact_Number, Zone_Org, Day_Applied, College_Level, MotherlName, MotherfName,
                    MothermName, FatherlName, FatherfName, FathermName, ConNum_M, ConNum_F,
                    OccupationM, OccupationF, EstAnnInc, AvgMonInc, Enroll_Paid, SemEstimate,
                    Age, recipient_ID);
                Alert.setText("Successfully added the recipient");
                action.Exit(SaveBtn);
            } else {
                Alert.setText("Recipient ID already taken");
            }
        } else {
            Alert.setText("Please fill out the required information properly");
        }
    }

    public void StoreData(String profile_Picture, String previous_Grades, String matriculation, String first_Name, String middle_Name, String last_Name,
            String CourseR, String SchoolAttend, String RStatus, String RAddress, int RZone, String RSex, String DateOB,
            String PlaceOB, String Contact_Number, String Zone_Org, String Day_Applied, String College_Level, String MotherlName, String MotherfName,
            String MothermName, String FatherlName, String FatherfName, String FathermName, String ConNum_M, String ConNum_F,
            String OccupationM, String OccupationF, String EstAnnInc, String AvgMonInc, String Enroll_Paid, String SemEstimate,
            int Age, String recipient_ID) throws SQLException {
        PreparedStatement ps = null;
        String query = "INSERT INTO Financial_Aid (First_Name_R, Middle_Name_R, Last_Name_R, Course, School, Status, Adress, Zone, "
                + "Sex, DOB, PlaceOB, Contact_Number, Zone_Organization, Day_Applied, College_Level, Last_Name_M, First_Name_M, Middle_Name_M, "
                + "Last_Name_F, First_Name_F, Middle_Name_F, Contact_Number_M, Contact_Number_F, Occupation_M, Occupation_F, Est_Annual_Income, "
                + "Avg_Monthly_Inc, Enrollment_Fee_Paid, Sem_Estimate, Age_R, Grades, Matri, Image, Recipient_Id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
        try{
            ps = Connect.prepareStatement(query);
            ps.setString(1, first_Name);
            ps.setString(2, middle_Name);
            ps.setString(3, last_Name);
            ps.setString(4, CourseR);
            ps.setString(5, SchoolAttend);
            ps.setString(6, RStatus);
            ps.setString(7, RAddress);
            ps.setInt(8, RZone);
            ps.setString(9, RSex);
            ps.setString(10, DateOB);
            ps.setString(11, PlaceOB);
            ps.setString(12, Contact_Number);
            ps.setString(13, Zone_Org);
            ps.setString(14, Day_Applied);
            ps.setString(15, College_Level);
            ps.setString(16, MotherlName);
            ps.setString(17, MotherfName);
            ps.setString(18, MothermName);
            ps.setString(19, FatherlName);
            ps.setString(20, FatherfName);
            ps.setString(21, FathermName);
            ps.setString(22, ConNum_M);
            ps.setString(23, ConNum_F);
            ps.setString(24, OccupationM);
            ps.setString(25, OccupationF);
            ps.setString(26, EstAnnInc);
            ps.setString(27, AvgMonInc);
            ps.setString(28, Enroll_Paid);   
            ps.setString(29, SemEstimate);   
            ps.setInt(30, Age);
            ps.setString(31, previous_Grades); 
            ps.setString(32, matriculation); 
            ps.setString(33, profile_Picture);
            ps.setString(34, recipient_ID);
            ps.executeUpdate();
            action.Exit(SaveBtn);
        }catch(Exception e){
            e.printStackTrace();
            ps.close();
        }finally{
            ps.close();
        }
    }

    public String SaveProfile() {
        Image ProfileImage = Profile.getImage();
        String ProfileName = Id.getText() + ".png";
        File file = new File("src/brgy_abella_system/FinancialAid/Profile/" + ProfileName);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(ProfileImage, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "src/brgy_abella_system/FinancialAid/Profile/" + ProfileName;
    }

    public String SaveGrades() {
        Image ProfileImage = Profile.getImage();
        String ProfileName = Id.getText() + ".png";
        File file = new File("src/brgy_abella_system/FinancialAid/Grades/" + ProfileName);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(ProfileImage, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "src/brgy_abella_system/FinancialAid/Grades/" + ProfileName;
    }

    public String SaveMatri() {
        Image ProfileImage = Profile.getImage();
        String ProfileName = Id.getText() + ".png";
        File file = new File("src/brgy_abella_system/FinancialAid/Matri/" + ProfileName);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(ProfileImage, null), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "src/brgy_abella_system/FinancialAid/Matri/" + ProfileName;
    }

    @FXML
    private void ExitBtnAction(ActionEvent event) {
        action.Exit(ExitBtn);
    }

    @FXML
    private void handleDropGrades(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        grades.setImage(img);
    }

    @FXML
    private void handleDropMatri(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        matri.setImage(img);
    }

    @FXML
    private void handleDropProfile(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        Profile.setImage(img);
    }

    @FXML
    private void handledragOverMatri(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handledragOverProfile(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handledragOverGrades(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
}
