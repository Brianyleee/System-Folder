/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brgy_abella_system.FinancialAid;

import brgy_abella_system.Repeatables;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class GradesController implements Initializable {

    Repeatables action = new Repeatables();
    @FXML
    private ImageView ImageView;
    @FXML
    private Button ExitBtn;
    @FXML
    private Label Name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void GradeView(String File, String Name) {
        Image Profile = new Image(new File(File).toURI().toString());
        ImageView.setImage(Profile);
        this.Name.setText(Name.toUpperCase() + " Grades");
    }

    @FXML
    private void ExitBtnAction(ActionEvent event) {
        action.Exit(ExitBtn);
    }
}
