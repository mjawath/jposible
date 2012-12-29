/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.fx.transactions.vm;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import org.biz.invoicesystem.entity.master.Customer;

/**
 *
 * @author d
 */
public class CustomerFX {
 
    private Customer customer; 
    
    
    public final SimpleStringProperty id =  new SimpleStringProperty();
    public final SimpleStringProperty code=  new SimpleStringProperty();
    private SimpleStringProperty customerID=  new SimpleStringProperty();
    private SimpleStringProperty customerName=  new SimpleStringProperty();
    private SimpleStringProperty companyName=  new SimpleStringProperty();
    private SimpleStringProperty designation=  new SimpleStringProperty();
    private SimpleStringProperty type=  new SimpleStringProperty();
    private SimpleStringProperty title=  new SimpleStringProperty();
    private Double creditLimit;
    private Double discount;
    private SimpleStringProperty salesRep=  new SimpleStringProperty();
    private SimpleStringProperty address=  new SimpleStringProperty();
    
    private SimpleStringProperty city=  new SimpleStringProperty();
    private SimpleStringProperty district=  new SimpleStringProperty();
    private SimpleStringProperty nicno=  new SimpleStringProperty();
    private Date crtDate;
    private Date dob;
    //   Boolean isSentToMaster;
    private Boolean isDeleted;
    //  private SimpleStringProperty loggedinStaff;
    private SimpleStringProperty religion;
    private SimpleStringProperty groupOfCustomer;
    private SimpleStringProperty signatureImage;
    private SimpleStringProperty phone;
    private SimpleStringProperty mobile;
    private SimpleStringProperty email;
    private SimpleStringProperty picLocation;
    private SimpleStringProperty loyaltyCardNo;

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
 
    

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code .set(code) ;
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getCustomerID() {
        return customerID.get();
    }

    public void setCustomerID(String customerID) {
        this.customerID .set(customerID); 
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
   
    
}
