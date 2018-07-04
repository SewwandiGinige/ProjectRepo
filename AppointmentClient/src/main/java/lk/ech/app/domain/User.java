package lk.ech.app.domain;

/**
 * Created by se-9 on 12/23/2016.
 */
public class User {

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public User() {
        uid = "";
        password = "";
        firstName = "";
        surname = "";
        ins_Code = "";
        status = "";
        usertype = "";
        ins_branchCode = "";
        actionType = "";

    }

    public User(String uid, String password, String firstName, String surname, String ins_Code, String status, String usertype, String ins_branchCode) {
        this.uid = uid;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.ins_Code = ins_Code;
        this.status = status;
        this.usertype = usertype;
        this.ins_branchCode = ins_branchCode;
    }

    private String uid;
    private String password;
    private String firstName;
    private String surname;
    private String ins_Code;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIns_Code(String ins_Code) {
        this.ins_Code = ins_Code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setIns_branchCode(String ins_branchCode) {
        this.ins_branchCode = ins_branchCode;
    }

    private String status;
    private String usertype;
    private String ins_branchCode;
    private String actionType;

    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getIns_Code() {
        return ins_Code;
    }

    public String getStatus() {
        return status;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getIns_branchCode() {
        return ins_branchCode;
    }
}


