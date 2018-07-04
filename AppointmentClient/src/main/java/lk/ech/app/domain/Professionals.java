package lk.ech.app.domain;

/**
 * Created by se-9 on 2/6/2017.
 */
public class Professionals {
    private int astrologerCode;
    private String title;
    private String firstName;
    private String lastName;
    private int mobile;
    private int status;
    private String actionType;
    private String[] speciality;

    public String[] getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String[] speciality) {
        this.speciality = speciality;
    }

    public int getAstrologerCode() {
        return astrologerCode;
    }

    public void setAstrologerCode(int astrologerCode) {
        this.astrologerCode = astrologerCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
