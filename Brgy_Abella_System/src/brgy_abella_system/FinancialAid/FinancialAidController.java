/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.FinancialAid;

import brgy_abella_system.Blotter.ViewBlotterController;
import brgy_abella_system.Connector;
import brgy_abella_system.Employee.Employee;
import brgy_abella_system.Repeatables;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class FinancialAidController implements Initializable {

    ObservableList<Recipients> Recipients = FXCollections.observableArrayList();
    Repeatables action = new Repeatables();
    Connection Connect;

    public FinancialAidController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }

    @FXML
    private Button homeBtn;
    @FXML
    private Button residentBtn;
    @FXML
    private Button employeeBtn;
    @FXML
    private Button blotterBtn;
    @FXML
    private Button financialAidBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private TableView<Recipients> FinancialAidRecieverTable;
    @FXML
    private TextField searchBar;
    @FXML
    private Button RefreshBtn;
    @FXML
    private Button addEmployeeBtn;
    @FXML
    private Button viewBtn;
    @FXML
    private Button editBtn;
    @FXML
    private TableColumn<Recipients, String> Applicant_id;
    @FXML
    private TableColumn<Recipients, String> fullname;
    @FXML
    private TableColumn<Recipients, String> birthDay;
    @FXML
    private TableColumn<Recipients, String> collegeLevel;
    @FXML
    private TableColumn<Recipients, String> DayApplied;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(FinancialAidController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void homeBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Dashboard/Dashboard.fxml", homeBtn);
    }

    @FXML
    private void residentBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Resident/Resident.fxml", residentBtn);
    }

    @FXML
    private void employeeBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Employee/Employee.fxml", employeeBtn);
    }

    @FXML
    private void blotterBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("Blotter/Blotter.fxml", blotterBtn);
    }

    @FXML
    private void financialAidBtnAction(ActionEvent event) throws IOException {
        action.ChangeScene("FinancialAid/FinancialAid.fxml", financialAidBtn);
    }

    @FXML
    private void LogoutButtonAction(ActionEvent event) throws IOException {
        action.logout(LogoutBtn);
    }

    public String Recipient_Id;

    @FXML
    private void GetDataOnClick(MouseEvent event) throws IOException, SQLException {
        Recipients person = FinancialAidRecieverTable.getSelectionModel().getSelectedItem();
        try {
            Recipient_Id = person.getRecipient_Id();
        } catch (Exception e) {
            Recipient_Id = null;
        }
    }

    @FXML
    private void SearchData(KeyEvent event) throws SQLException {
        String Search = searchBar.getText();
        Recipients.clear();
        String query = "SELECT * FROM Financial_Aid WHERE Recipient_Id LIKE ? OR"
                + " First_Name_R LIKE ? OR"
                + " Middle_Name_R LIKE ? OR"
                + " Last_Name_R LIKE ? OR"
                + " Status Like ?";
        PreparedStatement ps = Connect.prepareStatement(query);
        ps.setString(1, "%" + Search + "%");
        ps.setString(2, "%" + Search + "%");
        ps.setString(3, "%" + Search + "%");
        ps.setString(4, "%" + Search + "%");
        ps.setString(5, "%" + Search + "%");
        ResultSet rs = ps.executeQuery();

        try {
            while (rs.next()) {
                String id, fullname, DayApplied, fname, mname, lname,DOB,CollegeLevel;
                id = rs.getString("Recipient_Id");
                fname = rs.getString("First_Name_R");
                mname = rs.getString("Middle_Name_R");
                lname = rs.getString("Last_Name_R");
                DayApplied = rs.getString("Day_Applied");
                DOB = rs.getString("DOB");
                CollegeLevel = rs.getString("College_Level");
                fullname = lname+", "+fname+" "+mname.charAt(0)+".";
//                String Recipient_Id,String FullNameReci,String DayApplied,String DOB,String CollegeLevel
                Recipients.add(new Recipients(id,fullname,DayApplied,DOB,CollegeLevel));
                FinancialAidRecieverTable.setItems(Recipients);
            }
            Applicant_id.setCellValueFactory(new PropertyValueFactory<>("Recipient_Id"));
            fullname.setCellValueFactory(new PropertyValueFactory<>("FullNameReci"));
            DayApplied.setCellValueFactory(new PropertyValueFactory<>("DayApplied"));
            birthDay.setCellValueFactory(new PropertyValueFactory<>("DOB"));
            collegeLevel.setCellValueFactory(new PropertyValueFactory<>("CollegeLevel"));
        } catch (Exception e) {
            System.out.println(e);
            ps.close();
            rs.close();
        } finally {
            ps.close();
            rs.close();
        }
    }

    @FXML
    private void RefreshBtnAction(ActionEvent event) throws SQLException {
        loadData();
    }

    @FXML
    private void AddBtnAction(ActionEvent event) {
    }

    @FXML
    private void ViewBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Financial_Aid WHERE Recipient_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, Recipient_Id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String fullname = rs.getString("First_Name_R") + " " + rs.getString("Middle_Name_R") + " " + rs.getString("Last_Name_R") + " [" + rs.getString("Recipient_Id") + "]";
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
                String MotherName = rs.getString("Last_Name_F") + ", " + rs.getString("First_Name_F") + " " + rs.getString("Middle_Name_F").charAt(0) + ".";
                String FatherName = rs.getString("Last_Name_F") + ", " + rs.getString("First_Name_F") + " " + rs.getString("Middle_Name_F").charAt(0) + ".";
                String ConNum_M = rs.getString("Contact_Number_M");
                String ConNum_F = rs.getString("Contact_Number_F");;
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewRecipient.fxml"));
                Parent root = loader.load();
                ViewRecipientsController ViewRecipientsController = loader.getController();

                ViewRecipientsController.display(fullname, CourseR, SchoolAttend, RStatus, RAddress, RZone, Sex,
                        DOB, POB, Contact_Number, Zone_Org, DayApplied, College_Level, MotherName,
                        ConNum_M, OccupationM, FatherName, ConNum_F, OccupationM, EstAnnInc, AvgMonInc,
                        EnrollPaid, SemEsti, Age, Filename, Grades, Matri);

                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                action.Draggable(stage, root);
                stage.show();
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
    private void editBtnAction(ActionEvent event) {
    }

    public void loadData() throws SQLException {
        refreshTable();
        Applicant_id.setCellValueFactory(new PropertyValueFactory<>("Recipient_Id"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("FullNameReci"));
        DayApplied.setCellValueFactory(new PropertyValueFactory<>("DayApplied"));
        birthDay.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        collegeLevel.setCellValueFactory(new PropertyValueFactory<>("CollegeLevel"));
    }

    private void refreshTable() throws SQLException {
        Recipients.clear();
        String query = "SELECT * FROM Financial_Aid";
        PreparedStatement ps = Connect.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String Recipient_Id, lName, mName, fName, DayApplied, fullName, CollegeLevel, DOB;
            Recipient_Id = rs.getString("Recipient_Id");
            lName = rs.getString("Last_Name_R");
            mName = rs.getString("Middle_Name_R");
            fName = rs.getString("First_Name_R");
            DayApplied = rs.getString("Day_Applied");
            CollegeLevel = rs.getString("College_Level");
            DOB = rs.getString("DOB");

            fullName = lName + ", " + fName + " " + mName.charAt(0) + ".";

            Recipients.add(new Recipients(Recipient_Id, fullName, DayApplied, DOB, CollegeLevel));
            FinancialAidRecieverTable.setItems(Recipients);
        }

    }

}
