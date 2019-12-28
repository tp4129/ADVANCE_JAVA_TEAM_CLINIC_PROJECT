/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckUsernameController implements Initializable {
    
    @FXML
    private TextField newUsernameInput;
    @FXML
    private Button submitBtn;
    @FXML
    private ImageView statusIcon;
    @FXML
    private Text statusText;

    ArrayList<String> usernames;
    private static DatabaseProfileDetails ak = new DatabaseProfileDetails();
    private ResultSet rs;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernames = new ArrayList<String>();
        try {
            ak.getObject();
        } catch (SQLException ex) {
            Logger.getLogger(CheckUsernameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs = ak.fetchAllUsernames();
            while(rs.next()){
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckUsernameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(String i:usernames){
            System.out.println(i.toString());
            newUsernameInput.addEventFilter(KeyEvent.KEY_RELEASED , usernameValidation(i.toString()));  
        }
    }    
    
    public EventHandler<KeyEvent> usernameValidation(final String username) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if(txt_TextField.getText().equals(username)){
                    statusIcon.setImage(new Image(getClass().getResourceAsStream("../View/images/error.png")));
                    submitBtn.setDisable(true);
                    statusText.setText("Username already exists.");
                }else{
                    statusIcon.setImage(null);
                    statusText.setText("");
                    submitBtn.setDisable(false);
                }
            }
        };
    }    
    
}
