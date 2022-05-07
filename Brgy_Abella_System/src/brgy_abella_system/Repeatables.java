
package brgy_abella_system;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Repeatables {
    private double x, y = 0;
    
    public void Draggable(Stage stage, Parent root) {
        root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void ChangeScene(String FXMLname, Button BtnName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(FXMLname));
        Stage window = (Stage) BtnName.getScene().getWindow();
        window.setScene(new Scene(root));
        Draggable(window, root);
    }
    
    public void Exit(Button Button){
        Stage stage = (Stage) Button.getScene().getWindow();
        stage.close();
    }
}
