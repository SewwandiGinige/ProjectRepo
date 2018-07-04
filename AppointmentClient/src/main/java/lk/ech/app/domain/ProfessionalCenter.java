package lk.ech.app.domain;

/**
 * Created by se-9 on 1/6/2017.
 */
public class ProfessionalCenter {
    private String center_Code;
    private String center_Name;
    private String short_Code;

    public String getCenter_Code() {
        return center_Code;
    }

    public void setCenter_Code(String center_Code) {
        this.center_Code = center_Code;
    }

    public String getCenter_Name() {
        return center_Name;
    }

    public void setCenter_Name(String center_Name) {
        this.center_Name = center_Name;
    }

    public String getShort_Code() {
        return short_Code;
    }

    public void setShort_Code(String short_Code) {
        this.short_Code = short_Code;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String address1;
    private String address2;
    private String address3;
    private String status;
    private String city;
    private int serviceCode;

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }
}
