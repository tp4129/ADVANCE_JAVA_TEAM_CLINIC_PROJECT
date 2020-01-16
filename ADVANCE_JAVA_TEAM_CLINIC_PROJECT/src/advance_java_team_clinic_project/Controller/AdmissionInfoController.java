/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AdmissionInfoController implements Initializable {

    @FXML
    private DatePicker admissionDateInput;
    @FXML
    private DatePicker dischargeDateInput;
    @FXML
    private TextField roomInput;
    @FXML
    private TextField bedInput;
    @FXML
    private TextField costPerDayinput;
    @FXML
    private TextField totalCostInput;
    @FXML
    private TextField createdByInput;
    @FXML
    private TextField updatedByInput;
    @FXML
    private DatePicker createdDateInput;
    @FXML
    private DatePicker updatedDateInput;
    @FXML
    private AnchorPane admissionInfoPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setAdmissionID(String id){
        System.out.println(id);
    }
    
}