/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class PatientsRecordsController implements Initializable{
    

    
    private DatabaseLoginRecords  ak;
    private ResultSet rs;
    private ObservableList data;  
    @FXML
    private TableView<Records> recordsTable = new TableView<>();    
      
    TableColumn idCol = new TableColumn("id");
    TableColumn appDateCol = new TableColumn("APP DATE");
    TableColumn commentsCol = new TableColumn("COMMENTS");
    TableColumn appCodeCol = new TableColumn("APP CODE");
    TableColumn createdDateCol = new TableColumn("CREATED");
    TableColumn updatedDateCol = new TableColumn("UPDATED");
    TableColumn patientCol = new TableColumn("PATIENT");
    TableColumn doctorCol = new TableColumn("DOCTOR");
    TableColumn updatedByCol = new TableColumn("UPDATED BY");
    TableColumn createdByCol = new TableColumn("CREATED BY");
    
 
    public void initialize(URL location, ResourceBundle resources) {
       
       idCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
       appDateCol.setCellValueFactory(new PropertyValueFactory<>("app_date"));
       commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
       appCodeCol.setCellValueFactory(new PropertyValueFactory<>("app_code"));
       createdDateCol.setCellValueFactory(new PropertyValueFactory<>("created"));
       updatedDateCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
       patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));
       doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
       updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updated_by"));
       createdByCol.setCellValueFactory(new PropertyValueFactory<>("created_by"));
       
       
       Callback<TableColumn<Records, Void>, TableCell<Records, Void>>  cellFactory = new Callback<TableColumn<Records,Void>, TableCell<Records,Void>>(){
           @Override
           public TableCell<Records, Void> call(TableColumn<Records, Void> param) {
               final TableCell<Records, Void> cell = new TableCell<Records, Void>() {

                    private final Button btn = new Button();

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Records data = new Records();
                            data = getTableView().getItems().get(getIndex());
                            btn.setText(data.idProperty().getValue());
                            btn.setOnMouseClicked((MouseEvent event) -> {
                                try {                               
                                    FXMLLoader loader = new FXMLLoader(PatientsRecordsController.this.getClass().getResource("../View/id_RecordView.fxml"));
                                    Parent root = (Parent)loader.load();
                                    Stage recordStage = new Stage();
                                    Id_RecordViewController id = loader.getController();
                                    id.setLabelText(btn.getText());
                                    Scene scene = new Scene(root);
                                    recordStage.setTitle("ID RECORD");
                                    recordStage.setScene(scene);
                                    recordStage.setResizable(false);
                                    recordStage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }); 
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
           }
       };
       
       
        
        try {
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData();
            data = FXCollections.observableArrayList(databaseRecords(rs));
           
            idCol.setCellFactory(cellFactory);
            recordsTable.getColumns().add(idCol);
            recordsTable.getColumns().addAll(appDateCol,commentsCol,appCodeCol,
                    createdDateCol,updatedDateCol,patientCol,doctorCol,updatedByCol,createdByCol);
            recordsTable.setItems(data);
            
           
      

        } catch (SQLException ex) {
            Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }  
    
    /**
     * Returns data from a database
     * @param rs
     * @return
     * @throws SQLException 
     */
    private ArrayList databaseRecords(ResultSet rs) throws SQLException {
        ArrayList<Records> data = new ArrayList();
        
        while(rs.next()){
            Records record = new Records();
            record.idProperty().set(rs.getString("id"));
            record.app_dateProperty().set(rs.getString("app_date"));
            record.commentsProperty().set(rs.getString("comments"));
            record.app_codeProperty().set(rs.getString("app_code"));
            record.createdProperty().set(rs.getString("created"));
            record.updatedProperty().set(rs.getString("updated"));
            record.patientProperty().set(rs.getString("patient"));
            record.doctorProperty().set(rs.getString("doctor"));
            record.updated_byProperty().set(rs.getString("updated_by"));
            record.created_byProperty().set(rs.getString("created_by"));
            data.add(record);
        }
        return data;
    }
    

}


