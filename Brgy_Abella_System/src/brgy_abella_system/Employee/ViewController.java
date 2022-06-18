package brgy_abella_system.Employee;

import brgy_abella_system.Repeatables;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewController implements Initializable {

    Repeatables action = new Repeatables();
    @FXML
    private Button cancelBtn;
    @FXML
    private Label Emp_Id;
    @FXML
    private Label DOB;
    @FXML
    private Label Status;
    @FXML
    private Label Access;
    @FXML
    private Label Hired;
    @FXML
    private Label Resigned;

    Connection Connect;
    @FXML
    private Label name;
    @FXML
    private Label position;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }
    
    public void display(String fname, String mname,String lname,String id,String DB,int acc,String Designation,String Emp_Status,String emp_hired,String emp_resigned){
        name.setText(lname + ", " + fname+" "+mname);
        position.setText(Designation);
        Emp_Id.setText(id);
        DOB.setText(DB);
        Status.setText(Emp_Status);
        Access.setText(Integer.toString(acc));
        Hired.setText(emp_hired);
        Resigned.setText(emp_resigned);
    }
    

}
