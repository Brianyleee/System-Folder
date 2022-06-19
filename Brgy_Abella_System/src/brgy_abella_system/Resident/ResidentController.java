package brgy_abella_system.Resident;

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

public class ResidentController implements Initializable {

    ObservableList<Resident> ResidentList = FXCollections.observableArrayList();
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
    private TableView<Resident> residentTable;
    @FXML
    private TableColumn<Resident, Integer> residentId;
    @FXML
    private TableColumn<Resident, String> residentName;
    @FXML
    private TableColumn<Resident, String> residentAddress;
    @FXML
    private TableColumn<Resident, String> residentContactNo;
    @FXML
    private TextField searchBar;
    @FXML
    private Button RefreshBtn;
    @FXML
    private Button addResidentBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button viewBtn;

    public ResidentController() {
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
            Logger.getLogger(ResidentController.class.getName()).log(Level.SEVERE, null, ex);
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
        residentId.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        residentName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        residentAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        residentContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
    }
    
    private void refreshTable() throws SQLException {
        ResidentList.clear();
        String query = "SELECT * FROM Resident";
        PreparedStatement ps = Connect.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            String resident_Id, fName, mName, lName, houseNumber, street, zone, barangay, city, contactNum, fullName, fullAddress;
            
            resident_Id = rs.getString("Resident_Id");
            
            fName = rs.getString("First_Name");
            mName = rs.getString("Middle_Name");
            lName = rs.getString("Last_Name");
            
            houseNumber = rs.getString("House_No");
            street = rs.getString("Street");
            barangay = rs.getString("Barangay");
            zone = rs.getString("Zone");
            city = rs.getString("City");
            
            contactNum = rs.getString("Contact_No");
            
            fullName = lName + ", " + fName + " " + mName.charAt(0) + ".";
            
            if (houseNumber == null) {
                fullAddress = street + " Zone " + zone + " " + barangay + " " + city;
            } else {
                fullAddress = houseNumber + " " + street + " Zone " + zone + " " + barangay + " " + city;
            }
            
            ResidentList.add(new Resident(resident_Id, fullName, fullAddress, contactNum));
            residentTable.setItems(ResidentList);
        }
    }
    
    public String residentIdentification;

    @FXML
    private void GetDataOnClick(MouseEvent event) throws IOException, SQLException {
        Resident person = residentTable.getSelectionModel().getSelectedItem();
        try {
            residentIdentification = person.getResidentId();
        } catch (Exception e) {
            residentIdentification = null;
        }
    }

    @FXML
    private void SearchData(KeyEvent event) throws SQLException {
        String Search = searchBar.getText();
        ResidentList.clear();
        String query = "SELECT * FROM Resident WHERE Resident_Id LIKE ? OR"
                + " First_Name LIKE ? OR"
                + " Middle_Name LIKE ? OR"
                + " Last_Name LIKE ? OR"
                + " Contact_No LIKE ?";
        PreparedStatement ps = Connect.prepareStatement(query);
        ps.setString(1, "%" + Search + "%");
        ps.setString(2, "%" + Search + "%");
        ps.setString(3, "%" + Search + "%");
        ps.setString(4, "%" + Search + "%");
        ps.setString(5, "%" + Search + "%");
        ResultSet rs = ps.executeQuery();
        
        try {
            while (rs.next()) {
                String resident_Id, fName, mName, lName, houseNumber, street, zone, barangay, city, contactNum, fullName, fullAddress;
            
                resident_Id = rs.getString("Resident_Id");
            
                fName = rs.getString("First_Name");
                mName = rs.getString("Middle_Name");
                lName = rs.getString("Last_Name");
            
                houseNumber = rs.getString("House_No");
                street = rs.getString("Street");
                barangay = rs.getString("Barangay");
                zone = rs.getString("Zone");
                city = rs.getString("City");
            
                contactNum = rs.getString("Contact_No");
            
                fullName = lName + ", " + fName + " " + mName.charAt(0) + ".";
            
                if (houseNumber == null) {
                    fullAddress = street + " Zone " + zone + " " + barangay + " " + city;
                } else {
                    fullAddress = houseNumber + " " + street + " Zone " + zone + " " + barangay + " " + city;
                }
            
                ResidentList.add(new Resident(resident_Id, fullName, fullAddress, contactNum));
                residentTable.setItems(ResidentList);
                
            }
            residentId.setCellValueFactory(new PropertyValueFactory<>("residentId"));
            residentName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            residentAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
            residentContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
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
        Parent root = FXMLLoader.load(getClass().getResource("AddResident.fxml"));
        window.setScene(new Scene(root));
        window.initStyle(StageStyle.UNDECORATED);
        action.Draggable(window, root);
        window.show();
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Resident WHERE Resident_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, residentIdentification);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String resident_ID = rs.getString("Resident_Id");
                
                String first_Name = rs.getString("First_Name");
                String middle_Name = rs.getString("Middle_Name");
                String last_Name = rs.getString("Last_Name");
                
                String house_Number = rs.getString("House_No");
                String street = rs.getString("Street");
                String zone = rs.getString("Zone");
                String barangay = rs.getString("Barangay");
                String city = rs.getString("City");
                
                String contact_Number = rs.getString("Contact_No");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditResident.fxml"));
                Parent root = loader.load();
                
                EditResidentController EditResidentController = loader.getController();
                EditResidentController.Display(resident_ID);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                action.Draggable(stage, root);
                stage.show();
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
        String query = "SELECT * FROM Resident WHERE Resident_Id = ?";
        try {
            ps = Connect.prepareStatement(query);
            ps.setString(1, residentIdentification);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String residentID = rs.getString("Resident_Id");
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewResident.fxml"));
                Parent root = loader.load();
                
                ViewResidentController ViewResidentController = loader.getController();
                ViewResidentController.Display(resident_Image, residentID, employeeID, first_Name, middle_Name, last_Name, 
                        gender, birthDate, marital_Status, contact_Number, house_Number, street, zone, barangay, city);
                
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
    
}
