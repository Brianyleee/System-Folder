
package brgy_abella_system.FinancialAid;

import brgy_abella_system.Repeatables;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.*;

public class ViewRecipientsController implements Initializable {

    private String Grades, Name,Matri;
    Repeatables action = new Repeatables();
    @FXML
    private Label FullName;
    @FXML
    private Label Course;
    @FXML
    private Label School;
    @FXML
    private Label Status;
    @FXML
    private TextField Address;
    @FXML
    private Label Zone;
    @FXML
    private Label Sex;
    @FXML
    private Label DOB;
    @FXML
    private TextField POB;
    @FXML
    private Label ConNum;
    @FXML
    private TextField ZoneOrg;
    @FXML
    private Label DayApp;
    @FXML
    private Label CollegeLvl;
    @FXML
    private TextField MotherName;
    @FXML
    private Label ConNumM;
    @FXML
    private TextField OccupationM;
    @FXML
    private TextField FatherName;
    @FXML
    private Label ConNumF;
    @FXML
    private TextField OccupationF;
    @FXML
    private TextField EstAnnIncome;
    @FXML
    private TextField AvgMonInc;
    @FXML
    private TextField EnrollPaid;
    @FXML
    private TextField SemEsti;
    @FXML
    private Button matriculationBtn;
    @FXML
    private Button GradesBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Circle RecieverImg;
    @FXML
    private Label Age;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        action.Exit(cancelBtn);
    }

    public void display(String fullname, String CourseR, String SchoolAttend, String RStatus, String RAddress, int RZone, String Sex,
            String DOB, String POB, String Contact_Number, String Zone_Org, String DayApplied, String College_Level, String MotherName,
            String ConNum_M, String OccupationM, String FatherName, String ConNum_F, String OccupationF, String EstAnnInc, String AvgMonInc,
            String EnrollPaid, String SemEsti, int Age, String Profile,String Grades,String Matri) {
        try {
            FullName.setText(fullname);
            Course.setText(CourseR);
            School.setText(SchoolAttend);
            Status.setText(RStatus);
            Address.setText(RAddress);
            Zone.setText(String.valueOf(RZone));
            this.Sex.setText(Sex);
            this.DOB.setText(DOB);
            this.POB.setText(POB);
            ConNum.setText(Contact_Number);
            ZoneOrg.setText(Zone_Org);
            DayApp.setText(DayApplied);
            CollegeLvl.setText(College_Level);
            this.MotherName.setText(MotherName);
            ConNumM.setText(ConNum_M);
            this.OccupationM.setText(OccupationM);
            this.FatherName.setText(FatherName);
            ConNumF.setText(ConNum_F);
            this.OccupationF.setText(OccupationF);
            EstAnnIncome.setText(EstAnnInc);
            this.AvgMonInc.setText(AvgMonInc);
            this.EnrollPaid.setText(EnrollPaid);
            this.SemEsti.setText(SemEsti);
            this.Age.setText(String.valueOf(Age));
            
            Image ProfileView = new Image(new File(Profile).toURI().toString());
            RecieverImg.setFill(new ImagePattern(ProfileView));
            
            this.Grades = Grades;
            this.Matri = Matri;
            this.Name = fullname;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void matriculationBtnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MatriculationImage.fxml"));
        Parent root = loader.load();
        MatriculationImageController MatriculationImageController = loader.getController();
        MatriculationImageController.MatriculationView(Matri,Name);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        action.Draggable(stage, root);
        stage.show();
    }

    @FXML
    private void GradesBtnAction(ActionEvent event) throws IOException {
        System.out.println(Grades);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GradesImage.fxml"));
        Parent root = loader.load();
        GradesController GradesController = loader.getController();
        GradesController.GradeView(Grades,Name);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        action.Draggable(stage, root);
        stage.show();
        
    }

}
