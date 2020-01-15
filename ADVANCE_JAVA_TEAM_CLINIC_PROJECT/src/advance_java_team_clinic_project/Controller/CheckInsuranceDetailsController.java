   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import static advance_java_team_clinic_project.Controller.EditProfileController.LOCAL_DATE;
import advance_java_team_clinic_project.Model.DatabaseInsuranceDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckInsuranceDetailsController implements Initializable {

    @FXML
    private Button submitBtn;
    @FXML
    private DatePicker insuranceExpiredDate;
    @FXML
    private ComboBox european;
    @FXML
    private ComboBox ekas;
    @FXML
    private TextArea insureanceComments;

    private int euro=0,eka=0,euroc=0,ekac=0;
    private static DatabaseInsuranceDetails ak = new DatabaseInsuranceDetails();
    private ResultSet rs;
    User user = User.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(CheckAddressDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        submitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {      
        euro = european.getSelectionModel().getSelectedItem().toString().compareTo("no"); //An einai oxi to euro ginete 0
       System.out.println(euro);
            if(euro!=0){euro = 1;}
        eka = ekas.getSelectionModel().getSelectedItem().toString().compareTo("no"); //An einai oxi to eka ginete 0
            if(eka!=0){eka = 1;}
            try {
                ak.updateInsuranceDetails(user.getId(), insuranceExpiredDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), euro, eka, insureanceComments.getText());
            } catch (SQLException ex) {
                Logger.getLogger(CheckInsuranceDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            insureanceComments.clear();
        }
        });
    }    
    
       private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchInsuranceInfoData(user.getId());
        System.out.println("irthe");
        if (rs.next()) {
            insuranceExpiredDate.setValue(LOCAL_DATE(rs.getString("ins_expire_date")));
            european.getItems().clear();
            euroc = rs.getInt("european");
            if(euroc==0){european.getItems().addAll("no");}
            else{european.getItems().addAll("yes");}
            ekac = rs.getInt("ekas");
             if(ekac==0){ekas.getItems().addAll("no");}
            else{ekas.getItems().addAll("yes");}
            insureanceComments.setText(rs.getString("ins_comments"));
        } 
}
}

