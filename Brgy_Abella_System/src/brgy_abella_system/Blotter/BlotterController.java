package brgy_abella_system.Blotter;

import brgy_abella_system.Connector;
import brgy_abella_system.Repeatables;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BlotterController implements Initializable {
    
    ObservableList<Blotter> BlotterList = FXCollections.observableArrayList();
    Repeatables action = new Repeatables();
    Connection Connect;
    
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
    private TableView<Blotter> blotterTable;
    @FXML
    private TableColumn<Blotter, String> caseNo;
    @FXML
    private TableColumn<Blotter, String> complainant;
    @FXML
    private TableColumn<Blotter, String> defendant;
    @FXML
    private TableColumn<Blotter, String> date;
    @FXML
    private TextField searchBar;
    @FXML
    private Button RefreshBtn;
    @FXML
    private Button addBlotterBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button viewBtn;
    

    public BlotterController() {
        Connect = Connector.Connect();
        if (Connect == null) {
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(BlotterController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void loadData() throws SQLException {
        refreshTable();
        caseNo.setCellValueFactory(new PropertyValueFactory<>("caseNo"));
        complainant.setCellValueFactory(new PropertyValueFactory<>("fullNameC"));
        defendant.setCellValueFactory(new PropertyValueFactory<>("fullNameD"));
        date.setCellValueFactory(new PropertyValueFactory<>("complaintDate"));
    }
    
    private void refreshTable() throws SQLException {
        BlotterList.clear();
        String query = "SELECT * FROM Blotter";
        PreparedStatement ps = Connect.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            String caseNo, lNameC,mNameC,fNameC, lNameD,mNameD,fNameD, comDate,fullNameC,fullNameD;
            caseNo = rs.getString("Case_No");
            lNameC = rs.getString("Last_Name_C");
            mNameC = rs.getString("Middle_Name_C");
            fNameC = rs.getString("First_Name_C");
            
            lNameD = rs.getString("Last_Name_D");
            mNameD = rs.getString("Middle_Name_D");
            fNameD = rs.getString("First_Name_D");
            
            comDate = rs.getString("Complaint_Date");
            
            fullNameC = lNameC+", "+fNameC+" "+mNameC.charAt(0)+".";
            fullNameD = lNameD+", "+fNameD+" "+mNameD.charAt(0)+".";
            
            BlotterList.add(new Blotter(caseNo, fullNameC, fullNameD, comDate));
            blotterTable.setItems(BlotterList);
        }
        
    }
    public String caseNum;
    
    @FXML
    private void GetDataOnClick(MouseEvent event) throws IOException, SQLException {
        Blotter person = blotterTable.getSelectionModel().getSelectedItem();
        try {
            caseNum = person.getCaseNo();
        } catch (Exception e) {
            caseNum = null;
        }   
    }

    @FXML
    private void SearchData(KeyEvent event) throws SQLException {
        String Search = searchBar.getText();
        BlotterList.clear();
        String query = "SELECT * FROM Blotter WHERE Case_No LIKE ? OR"
                + " Last_Name_C LIKE ? OR"
                + " Middle_Name_C LIKE ? OR"
                + " First_Name_C LIKE ? OR"
                + " Last_Name_D LIKE ? OR"
                + " Middle_Name_D LIKE ? OR"
                + " First_Name_D LIKE ? OR"
                + " Complaint_Date LIKE ?";
        PreparedStatement ps = Connect.prepareStatement(query);
        ps.setString(1, "%" + Search + "%");
        ps.setString(2, "%" + Search + "%");
        ps.setString(3, "%" + Search + "%");
        ps.setString(4, "%" + Search + "%");
        ps.setString(5, "%" + Search + "%");
        ps.setString(6, "%" + Search + "%");
        ps.setString(7, "%" + Search + "%");
        ps.setString(8, "%" + Search + "%");
        ResultSet rs = ps.executeQuery();
        
        try {
            while (rs.next()) {
                String caseNo, lNameC,mNameC,fNameC, lNameD,mNameD,fNameD, comDate,fullNameC,fullNameD;
                caseNo = rs.getString("Case_No");
                lNameC = rs.getString("Last_Name_C");
                mNameC = rs.getString("Middle_Name_C");
                fNameC = rs.getString("First_Name_C");
            
                lNameD = rs.getString("Last_Name_D");
                mNameD = rs.getString("Middle_Name_D");
                fNameD = rs.getString("First_Name_D");
            
                comDate = rs.getString("Complaint_Date");
            
                fullNameC = lNameC+", "+fNameC+" "+mNameC.charAt(0)+".";
                fullNameD = lNameD+", "+fNameD+" "+mNameD.charAt(0)+".";
            
                BlotterList.add(new Blotter(caseNo, fullNameC, fullNameD, comDate));
                blotterTable.setItems(BlotterList);
            }
            caseNo.setCellValueFactory(new PropertyValueFactory<>("caseNo"));
            complainant.setCellValueFactory(new PropertyValueFactory<>("fullNameC"));
            defendant.setCellValueFactory(new PropertyValueFactory<>("fullNameD"));
            date.setCellValueFactory(new PropertyValueFactory<>("complaintDate"));
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
    private void AddBtnAction(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddBlotter.fxml"));
        window.setScene(new Scene(root));
        window.initStyle(StageStyle.UNDECORATED);
        action.Draggable(window, root);
        window.show();
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Blotter WHERE Case_No = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, caseNum);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String case_Number = rs.getString("Case_No");
                String complaint_Date = rs.getString("Complaint_Date");
                String complaint = rs.getString("Complaint");
                String solution = rs.getString("Solution");
                String employee_ID = rs.getString("Employee_Id");
                
                String complainant_ID_C = rs.getString("Complainant_Id");
                String first_Name_C = rs.getString("First_Name_C");
                String middle_Name_C = rs.getString("Middle_Name_C");
                String last_Name_C = rs.getString("Last_Name_C");
                String contact_Number_C = rs.getString("Contact_No_C");
                String street_C = rs.getString("Street_C");
                String barangay_C = rs.getString("Barangay_C");
                String city_C = rs.getString("City_C");
                String province_C = rs.getString("Province_C");
                
                String first_Name_D = rs.getString("First_Name_D");
                String middle_Name_D = rs.getString("Middle_Name_D");
                String last_Name_D = rs.getString("Last_Name_D");
                String contact_Number_D = rs.getString("Contact_No_D");
                String street_D = rs.getString("Street_D");
                String barangay_D = rs.getString("Barangay_D");
                String city_D = rs.getString("City_D");
                String province_D = rs.getString("Province_D");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditBlotter.fxml"));
                Parent root = loader.load();
                EditBlotterController EditBlotterController = loader.getController();
                EditBlotterController.display(case_Number, complaint_Date, complaint, solution, employee_ID, 
                        complainant_ID_C, first_Name_C, middle_Name_C, last_Name_C, contact_Number_C, street_C, barangay_C, city_C, province_C, 
                        first_Name_D, middle_Name_D, last_Name_D, contact_Number_D, street_D, barangay_D, city_D, province_D);
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
            e.printStackTrace();
            System.out.println(e);
            ps.close();
            rs.close();
        } finally {
            ps.close();
            rs.close();
        }
    }

    @FXML
    private void ViewBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Blotter WHERE Case_No = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, caseNum);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String case_Number = rs.getString("Case_No");
                String complaint_Date = rs.getString("Complaint_Date");
                String complaint = rs.getString("Complaint");
                String solution = rs.getString("Solution");
                String employee_ID = rs.getString("Employee_Id");
                
                String complainant_ID_C = rs.getString("Complainant_Id");
                String first_Name_C = rs.getString("First_Name_C");
                String middle_Name_C = rs.getString("Middle_Name_C");
                String last_Name_C = rs.getString("Last_Name_C");
                String contact_Number_C = rs.getString("Contact_No_C");
                String street_C = rs.getString("Street_C");
                String barangay_C = rs.getString("Barangay_C");
                String city_C = rs.getString("City_C");
                String province_C = rs.getString("Province_C");
                
                String first_Name_D = rs.getString("First_Name_D");
                String middle_Name_D = rs.getString("Middle_Name_D");
                String last_Name_D = rs.getString("Last_Name_D");
                String contact_Number_D = rs.getString("Contact_No_D");
                String street_D = rs.getString("Street_D");
                String barangay_D = rs.getString("Barangay_D");
                String city_D = rs.getString("City_D");
                String province_D = rs.getString("Province_D");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBlotter.fxml"));
                Parent root = loader.load();
                ViewBlotterController ViewBlotterController = loader.getController();
                ViewBlotterController.display(case_Number, complaint_Date, complaint, solution, employee_ID, 
                        complainant_ID_C, first_Name_C, middle_Name_C, last_Name_C, contact_Number_C, street_C, barangay_C, city_C, province_C, 
                        first_Name_D, middle_Name_D, last_Name_D, contact_Number_D, street_D, barangay_D, city_D, province_D);
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

    
    
}
