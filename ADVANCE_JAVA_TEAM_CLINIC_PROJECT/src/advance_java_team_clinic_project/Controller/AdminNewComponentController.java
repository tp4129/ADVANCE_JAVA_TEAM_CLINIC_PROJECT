/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Main.Main;
import advance_java_team_clinic_project.Model.Parametrics;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AdminNewComponentController implements Initializable {

    @FXML
    private AnchorPane EditForm;
    @FXML
    private Button createBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextArea descText;

    Parametrics pm = new Parametrics();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    public void createComponent(String tableName){
        saveBtn.setVisible(false);
        deleteBtn.setVisible(false);
        createBtn.setVisible(true);
        createBtn.setOnMouseClicked((MouseEvent event) -> {
             pm.createComponent(tableName, descText.getText());            
             Stage stage = (Stage) EditForm.getScene().getWindow();
             EditForm.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }
    
    public void editComponent(String tableName, Integer id, String desc){
        saveBtn.setVisible(true);
        deleteBtn.setVisible(true);
        createBtn.setVisible(false);
        System.out.println(tableName + " " + id + " " + desc);
        descText.setText(desc);
        saveBtn.setOnMouseClicked((MouseEvent event) -> {
             pm.editComponent(tableName, descText.getText(), id);            
             Stage stage = (Stage) EditForm.getScene().getWindow();
             EditForm.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        
        deleteBtn.setOnMouseClicked((MouseEvent event) -> {
             pm.deleteComponent(tableName, id);            
             Stage stage = (Stage) EditForm.getScene().getWindow();
             EditForm.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }
    
}
