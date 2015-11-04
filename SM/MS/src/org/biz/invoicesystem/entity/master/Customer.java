package org.biz.invoicesystem.entity.master;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author mjawath
 */
@Entity
public class Customer extends Persons {

    private String code;
    private String customerID;
    private String customerName;
    private String companyName;
    private String designation;
    private String type;
    private String title;
    private Double creditLimit;
    private Double discount;
    private String salesRep;
    private String address;
    private String address2;
    private String city;
    private String district;
    private String nicno;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date crtDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    //   Boolean isSentToMaster;
    private Boolean isDeleted;
    //  private String loggedinStaff;
    private String religion;
    private String groupOfCustomer;
    private String signatureImage;
    private String phone;
    private String mobile;
    private String email;
    private String picLocation;
    private String loyaltyCardNo;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupOfCustomer() {
        return groupOfCustomer;
    }

    public void setGroupOfCustomer(String groupOfCustomer) {
        this.groupOfCustomer = groupOfCustomer;
    }

    public static Customer find(String code, List<Customer> lst) {


        Comparator<Customer> com = new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return o1.getId().toString().compareTo(o2.getId().toString());
            }
        };

        Collections.sort(lst, com);

        Customer s = new Customer();

        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    public void setLoyaltyCardNo(String loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    public String getNicno() {
        return nicno;
    }

    public void setNicno(String nicno) {
        this.nicno = nicno;
    }

    public String getPicLocation() {
        return picLocation;
    }

    public void setPicLocation(String picLocation) {
        this.picLocation = picLocation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
     
    
}
