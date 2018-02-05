package org.biz.invoicesystem.entity.master;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Staff extends User {

    private String code;
    private String name;
    private String nic;
    private String department;
    private String initial;
    private String gender;
    private String reigion;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String designation;
    private String address1;
    private String address2;
    private String city;
    private String password;
    private String retypePasswod;
    private String username;
    private String thumbImage;
    private String phone;
    private String mobile;
    @Temporal(TemporalType.DATE)
    private Date joinedDate;
    private Double salary;
    private Double allowance;
    private String paymentMethod;
    private String securityRole;
    private String signatureImage;
    //  private String otherDetails;
    @Temporal(TemporalType.DATE)
    Date crtdTime;
    //  private boolean isSentToMaster;
    //  private boolean isDeleted;
    //  private String workPlace;
    //  private String loggedinStaff;
    private String shopName;//shopname is because when we filter username we need to filter it by shopname
    //coz shp person in one location cant login to other shop....
    private String email;

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

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCrtdTime() {
        return crtdTime;
    }

    public void setCrtdTime(Date crtdTime) {
        this.crtdTime = crtdTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getRetypePasswod() {
        return retypePasswod;
    }

    public void setRetypePasswod(String retypePasswod) {
        this.retypePasswod = retypePasswod;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the reigion
     */
    public String getReigion() {
        return reigion;
    }

    /**
     * @param reigion the reigion to set
     */
    public void setReigion(String reigion) {
        this.reigion = reigion;
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName the shopName to set
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
