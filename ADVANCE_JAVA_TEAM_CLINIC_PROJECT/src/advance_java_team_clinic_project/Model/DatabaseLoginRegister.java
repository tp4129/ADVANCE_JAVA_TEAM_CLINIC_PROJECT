/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

public class DatabaseLoginRegister {

    private Statement stmt;
    private String sql,existSql,regSql;
    private ResultSet rs,regRs;
    private String username, password, role, created, updated;
    private DatabaseConnection object;
    User user = User.getInstance();
    public Integer roleId;
    Alert alert = new Alert(AlertType.INFORMATION);

    public void getObject() throws SQLException {
        object = DatabaseConnection.getInstance();
    } 
    public boolean loginQuery(String userName, String passWord) throws SQLException {
        /* Alert Initialization */
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        /* End of Alert Initialiization*/
        stmt = object.connection.createStatement();
        sql = "select id, password, role_id, name as firstname, surname, username from pm_users where username = '" + userName + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            password = rs.getString("password");
            if (password.equals(passWord)) {
                user.setRoleID(rs.getInt("role_id"));
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                return true;
            } else if (password != passWord) {
                alert.setTitle("Incorrect Password");
                alert.setContentText("The Password you have entered is not correct!");
                alert.showAndWait();
                return false;
            }
        } else {
            alert.setTitle("Incorrect Username");
            alert.setContentText("The Username you have entered does not match any existing user!");
            alert.showAndWait();
            return false;
        }
        return false;
    }
    
    public boolean registerQuery(String userName, String passWord) throws SQLException{
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        stmt = object.connection.createStatement();
        existSql = "select count(*) as existing from pm_users where username ='"+userName+"'";
        rs = stmt.executeQuery(existSql);
        rs.next();
        if (rs.getInt("existing") > 0){
            alert.setTitle("Wrong username");
            alert.setContentText("Username already existing, please add another username.");
            alert.showAndWait();
            return false;
        }else if (rs.getInt("existing") == 0){
            regSql = "insert into pm_users (username,password,role_id) values ('"+userName+"','"+passWord+"',3)";
            regRs = stmt.executeQuery(regSql);
            alert.setTitle("Success!");
            alert.setContentText("User was succesfully registered! You may login now.");
            alert.showAndWait();
            return true;

        }
        return false;
    }
}