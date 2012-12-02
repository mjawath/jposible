/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
//@DiscriminatorColumn
public class ContactDetail extends BusObj implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Contact_ID")
    List<Address> addresses;
    String phones;
    String defPhone;    
    String faxs;
    String defFax;  
    String websites; 
    String defwebsite; 
    String homePageWebsite; 
    String emails;
    String defEmail;
    String socialWebsite;
    
    @OneToOne
    Address defAddress;

    public ContactDetail() {
        addresses = new ArrayList<Address>();
        
    }

   


    public List<Address> getAddresses() {
        return addresses;
    }

    public Address getDefAddress() {
        return defAddress;
    }

    public void setDefAddress(Address defAddress) {
        this.defAddress = defAddress;
    }

    public String getDefEmail() {
        return defEmail;
    }

    public void setDefEmail(String defEmail) {
        this.defEmail = defEmail;
    }

    public String getDefFax() {
        return defFax;
    }

    public void setDefFax(String defFax) {
        this.defFax = defFax;
    }

    public String getDefPhone() {
        return defPhone;
    }

    public void setDefPhone(String defPhone) {
        this.defPhone = defPhone;
    }

    public String getDefwebsite() {
        return defwebsite;
    }

    public void setDefwebsite(String defwebsite) {
        this.defwebsite = defwebsite;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getFaxs() {
        return faxs;
    }

    public void setFaxs(String faxs) {
        this.faxs = faxs;
    }

    public String getHomePageWebsite() {
        return homePageWebsite;
    }

    public void setHomePageWebsite(String homePageWebsite) {
        this.homePageWebsite = homePageWebsite;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getSocialWebsite() {
        return socialWebsite;
    }

    public void setSocialWebsite(String socialWebsite) {
        this.socialWebsite = socialWebsite;
    }

    public String getWebsites() {
        return websites;
    }

    public void setWebsites(String websites) {
        this.websites = websites;
    }

    
    public  void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public static String addString(String list,String phone){
        if(list==null){list=""+phone;return list;}
        list+=divider+phone;
        return list;
    }
    public static String divider="\n";
}
