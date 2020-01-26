/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.LoggedInUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class PatientsDashboardController extends NewStage implements Initializable {

    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private Text usernameText;
    @FXML
    private TextFlow textPane;
    @FXML
    private BorderPane patientPane;
    @FXML
    private ToggleButton recordsBtn;
    @FXML
    private ToggleButton editProfileBtn;
    @FXML
    private ToggleButton logoutBtn;
    @FXML
    private ToggleButton makeAnAppointment;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUIonSamePane("../View/patientsRecords.fxml", patientPane);
        recordsBtn.setSelected(true);
        usernameText.setText(usernameText.getText() + user.getUsername());
        textPane.setTextAlignment(TextAlignment.CENTER);
        recordsBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/patientsRecords.fxml", patientPane);
            clearSelectedButtons();
            recordsBtn.setSelected(true);
        });

        editProfileBtn.setOnMouseClicked((MouseEvent event) -> {
            clearSelectedButtons();
            editProfileBtn.setSelected(true);
            FXMLLoader loader = new FXMLLoader(PatientsDashboardController.this.getClass().getResource("../View/editProfile.fxml"));
            Parent root = null;
            try {
                root = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            EditProfileController editController = loader.getController();
            editController.myInit(user.getId());
            patientPane.setCenter(root);
            patientPane.setCenter(root);
        
        });

        makeAnAppointment.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/Appointment.fxml", patientPane);
            clearSelectedButtons();
            makeAnAppointment.setSelected(true);
        });

        logoutBtn.setOnMouseClicked((MouseEvent event) -> {
            Stage currentStage = (Stage) patientPane.getScene().getWindow();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Do you want to Logout?");
            //alert.setContentText("");
            alert.initStyle(StageStyle.UTILITY);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    setNewStage("../View/loginStyleFX.fxml", currentStage);
                } catch (IOException ex) {
                    Logger.getLogger(PatientsDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                clearSelectedButtons();
            }
        });

    }

    void clearSelectedButtons() {
        recordsBtn.setSelected(false);
        editProfileBtn.setSelected(false);
        makeAnAppointment.setSelected(false);
        logoutBtn.setSelected(false);
    }
}
