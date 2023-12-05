package krg.petr.naumen.dto;

import java.time.LocalDate;
import java.util.List;

public class UserProfileDTO {

    private String userFirstName;

    private String userLastName;

    private String userPatName;

    private LocalDate userBirthDate;

    private String userGender;

    private String userDisplayName;

    private String userLogin;

    private String userEmail;

    private String userPhone;

    private List<String> allRoles;

    private String currentRole;

    private List<String> allDepartment;

    private String currentDepartment;

    private List<String> allDivision;

    private String currentDivision;

    private List<String> allPosition;

    private String currentPosition;


    public List<String> getAllDepartment() {
        return allDepartment;
    }

    public void setAllDepartment(List<String> allDepartment) {
        this.allDepartment = allDepartment;
    }

    public String getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(String currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    public List<String> getAllDivision() {
        return allDivision;
    }

    public void setAllDivision(List<String> allDivision) {
        this.allDivision = allDivision;
    }

    public String getCurrentDivision() {
        return currentDivision;
    }

    public void setCurrentDivision(String currentDivision) {
        this.currentDivision = currentDivision;
    }

    public List<String> getAllPosition() {
        return allPosition;
    }

    public void setAllPosition(List<String> allPosition) {
        this.allPosition = allPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public List<String> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<String> allRoles) {
        this.allRoles= allRoles;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPatName() {
        return userPatName;
    }

    public void setUserPatName(String userPatName) {
        this.userPatName = userPatName;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(LocalDate userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}