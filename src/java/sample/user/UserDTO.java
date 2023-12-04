/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String userID;
    private String fullName;
    private String roleID;
    private String passWord;

    public UserDTO() {
        this.userID="";
        this.fullName="";
        this.roleID="";
        this.passWord="";
    }

    public UserDTO(String userID, String fullName, String roleID, String passWord) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.passWord = passWord;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
