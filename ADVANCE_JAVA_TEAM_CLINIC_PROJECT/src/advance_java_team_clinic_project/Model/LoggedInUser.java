/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoggedInUser {

    private int id;
    private String username, name, surname, firstName;
    private int ruleID;
    private int addressID;
    private int contactID;


    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getContactID() {
        return contactID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getRuleID() {
        return ruleID;
    }

    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int roleID;
    private String code;
    private static LoggedInUser instance = null;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
   
    
    private LoggedInUser() {
    }

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }

        return instance;
    }

    public void setInstance(LoggedInUser instance) {
        LoggedInUser.instance = null;
    }

}