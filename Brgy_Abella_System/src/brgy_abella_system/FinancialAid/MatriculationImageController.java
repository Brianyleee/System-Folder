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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author xande
 */
public class MatriculationImageController implements Initializable {
    
    Repeatables action = new Repeatables();
    @FXML
    private ImageView ImageView;
    @FXML
    private Label Name;
    @FXML
    private Button ExitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MatriculationView(String File, String Name) {
        Image Profile = new Image(new File(File).toURI().toString());
        ImageView.setImage(Profile);
        this.Name.setText(Name.toUpperCase() + " Matriculation");
    }
    
    @FXML
    private void ExitBtnAction(ActionEvent event) {
        action.Exit(ExitBtn);
    }
    
}
