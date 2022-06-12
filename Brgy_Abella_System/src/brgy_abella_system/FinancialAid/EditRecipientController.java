/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.FinancialAid;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class EditRecipientController implements Initializable {

    ObservableList<String> StatusList = FXCollections.observableArrayList("Active", "Inactive");
    ObservableList<String> Sex = FXCollections.observableArrayList("Male", "Female");
    ObservableList<Integer> Zone = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    ObservableList<String> Level = FXCollections.observableArrayList("1st Year", "2nd Year", "3rd Year", "4th Year");
    ObservableList<String> Income = FXCollections.observableArrayList("Below 5,000", "5,000-10,000", "10,000-15,000", "Above 15,000");
    Repeatables action = new Repeatables();

    Connection Connect;
    @FXML
    private ImageView Profile;
    @FXML
    private TextField mname;
    @FXML
    private Label Alert;

    public EditRecipientController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    @FXML
    private Label Id;
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
    private TextField manme;
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
    private TextField OccupationF;
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

    public void Display(String RecipientId) throws SQLException {
        Id.setText(RecipientId);

        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Financial_Aid WHERE Recipient_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, RecipientId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fname = rs.getString("First_Name_R");
                String mname = rs.getString("Middle_Name_R");
                String lname = rs.getString("Last_Name_R");
                String CourseR = rs.getString("Course");
                String SchoolAttend = rs.getString("School");
                String RStatus = rs.getString("Status");
                String RAddress = rs.getString("Adress");
                int RZone = rs.getInt("Zone");
                String Sex = rs.getString("Sex");
                String DOB = rs.getString("DOB");
                String POB = rs.getString("PlaceOB");
                String Contact_Number = rs.getString("Contact_Number");
                String Zone_Org = rs.getString("Zone_Organization");
                String DayApplied = rs.getString("Day_Applied");
                String College_Level = rs.getString("College_Level");

                String MotherlName = rs.getString("Last_Name_M");
                String MotherfName = rs.getString("First_Name_M");
                String MothermName = rs.getString("Middle_Name_M");

                String FatherlName = rs.getString("Last_Name_F");
                String FatherfName = rs.getString("First_Name_F");
                String FathermName = rs.getString("Middle_Name_F");

                String ConNum_M = rs.getString("Contact_Number_M");
                String ConNum_F = rs.getString("Contact_Number_F");
                String OccupationM = rs.getString("Occupation_M");
                String OccupationF = rs.getString("Occupation_F");
                String EstAnnInc = rs.getString("Est_Annual_Income");
                String AvgMonInc = rs.getString("Avg_Monthly_Inc");
                String EnrollPaid = rs.getString("Enrollment_Fee_Paid");
                String SemEsti = rs.getString("Sem_Estimate");

                String Grades = rs.getString("Grades");
                String Matri = rs.getString("Matri");
                int Age = rs.getInt("Age_R");
                String Filename = rs.getString("Image");

                this.fname.setText(fname);
                this.mname.setText(mname);
                this.lname.setText(lname);
                this.status.setValue(RStatus);
                this.zone.setValue(RZone);
                this.sex.setValue(Sex);
                this.school.setText(SchoolAttend);
                this.course.setText(CourseR);
                this.address.setText(RAddress);
                this.DOB.setValue(LocalDate.parse(DOB));
                this.age.setText(Integer.toString(Age));
                this.POB.setText(POB);
                this.PhoneNumR.setText(Contact_Number);
                this.ZoneOrg.setText(Zone_Org);
                this.DayApplied.setValue(LocalDate.parse(DayApplied));
                this.CollegeLevel.setValue(College_Level);

                this.fnameM.setText(MotherfName);
                this.mnameM.setText(MothermName);
                this.lnameM.setText(MotherlName);
                this.PhoneNumM.setText(ConNum_M);
                this.occupationM.setText(OccupationM);

                this.fnameF.setText(FatherfName);
                this.mnameF.setText(FathermName);
                this.lnameF.setText(FatherlName);
                this.PhoneNumF.setText(ConNum_F);
                this.OccupationF.setText(OccupationF);

                this.AnnInc.setValue(EstAnnInc);
                this.AvgInc.setText(AvgMonInc);
                this.EnrollPaid.setText(EnrollPaid);
                this.SemEsti.setText(SemEsti);

                Image ProfileView = new Image(new File(Filename).toURI().toString());
                this.Profile.setImage(ProfileView);
                Image GradesView = new Image(new File(Grades).toURI().toString());
                this.grades.setImage(GradesView);
                Image MatriView = new Image(new File(Matri).toURI().toString());
                this.matri.setImage(MatriView);

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
    private void SaveBtnAction(ActionEvent event) {
        try {
            String Profile = SaveProfile();
            String Grades = SaveGrades();
            String Matri = SaveMatri();
            String Id = this.Id.getText();
            String fname = this.fname.getText();
            String mname = this.mname.getText();
            String lname = this.lname.getText();
            String CourseR = this.course.getText();
            String SchoolAttend = this.school.getText();
            String RStatus = this.status.getValue();
            String RAddress = this.address.getText();
            int RZone = this.zone.getValue();
            String Sex = this.sex.getValue();
            String DOB = this.DOB.getValue().toString();
            String POB = this.POB.getText();
            String Contact_Number = this.PhoneNumR.getText();
            String Zone_Org = this.ZoneOrg.getText();
            String DayApplied = this.DayApplied.getValue().toString();
            String College_Level = this.CollegeLevel.getValue();

            String MotherlName = this.lnameM.getText();
            String MotherfName = this.fnameM.getText();
            String MothermName = this.mname.getText();

            String FatherlName = this.lnameF.getText();
            String FatherfName = this.fnameF.getText();
            String FathermName = this.mnameF.getText();

            String ConNum_M = this.PhoneNumM.getText();
            String ConNum_F = this.PhoneNumF.getText();
            String OccupationM = this.occupationM.getText();
            String OccupationF = this.OccupationF.getText();
            String EstAnnInc = this.AnnInc.getValue();
            String AvgMonInc = this.AvgInc.getText();
            String EnrollPaid = this.EnrollPaid.getText();
            String SemEsti = this.SemEsti.getText();
            int Age = Integer.valueOf(this.age.getText());
            
            StoreData(Profile,Grades, Matri, fname, mname, lname,
             CourseR, SchoolAttend, RStatus, RAddress, RZone, Sex, DOB,
             POB, Contact_Number, Zone_Org, DayApplied, College_Level, MotherlName, MotherfName,
             MothermName, FatherlName, FatherfName, FathermName, ConNum_M, ConNum_F,
             OccupationM, OccupationF, EstAnnInc, AvgMonInc, EnrollPaid, SemEsti,
             Age, Id);

        } catch (Exception e) {
            Alert.setText("Something Went Wrong Please Enter Valid Answers in the Form");
        }

    }

    public void StoreData(String Profile,String Grades,String Matri,String fname,String mname,String lname,
            String CourseR,String SchoolAttend,String RStatus,String RAddress,int RZone,String Sex,String DOB,
            String POB,String Contact_Number,String Zone_Org,String DayApplied,String College_Level,String MotherlName,String MotherfName,
            String MothermName,String FatherlName,String FatherfName,String FathermName,String ConNum_M,String ConNum_F,
            String OccupationM,String OccupationF,String EstAnnInc,String AvgMonInc,String EnrollPaid,String SemEsti,
            int Age,String Id) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "UPDATE Financial_Aid SET "
                + "First_Name_R=?,"+"Middle_Name_R=?,"+ "Last_Name_R=?,"
                + "Course=?,"
                + "School=?,"
                + "Status=?,"
                + "Adress=?,"
                + "Zone=?,"
                + "Sex=?,"
                + "DOB=?,"
                + "PlaceOB=?,"
                + "Contact_Number=?,"
                + "Zone_Organization=?,"
                + "Day_Applied=?,"
                + "College_Level=?,"
                + "Last_Name_M=?,"+ "First_Name_M=?,"+ "Middle_Name_M=?,"
                + "Last_Name_F=?,"+ "First_Name_F=?,"+ "Middle_Name_F=?,"
                + "Contact_Number_M=?,"
                + "Contact_Number_F=?,"
                + "Occupation_M=?,"
                + "Occupation_F=?,"
                + "Est_Annual_Income=?,"
                + "Avg_Monthly_Inc=?,"
                + "Enrollment_Fee_Paid=?,"
                + "Sem_Estimate=?,"
                + "Age_R=?,"
                + "Grades=?,"
                + "Matri=?,"
                + "Image=?"
                + " WHERE Recipient_Id=?";
        try{
            ps = Connect.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, mname);
            ps.setString(3, lname);
            ps.setString(4, CourseR);
            ps.setString(5, SchoolAttend);
            ps.setString(6, RStatus);
            ps.setString(7, RAddress);
            ps.setInt(8, RZone);
            ps.setString(9, Sex);
            ps.setString(10, DOB);
            ps.setString(11, POB);
            ps.setString(12, Contact_Number);
            ps.setString(13, Zone_Org);
            ps.setString(14, DayApplied);
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
            ps.setString(28,EnrollPaid);   
            ps.setString(29,SemEsti);   
            ps.setInt(30,Age);
            ps.setString(31,Grades); 
            ps.setString(32,Matri); 
            ps.setString(33,Profile);
            ps.setString(34,Id);
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
