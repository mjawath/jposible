 
package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
public class Vehicle implements Serializable {
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
 
    String regNo;
    
    String vehicleType;
    
      String model;
      
        String chessieNo;
        String engineNo;
      
      @Temporal(TemporalType.DATE)
      Date licenceExpire;    
        
      @Temporal(TemporalType.DATE)
      Date insuranceExpire;
      String ownerShip;
        String ownerPhone;
        
       String driver;
       
       
      
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getChessieNo() {
        return chessieNo;
    }

    public void setChessieNo(String chessieNo) {
        this.chessieNo = chessieNo;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getInsuranceExpire() {
        return insuranceExpire;
    }

    public void setInsuranceExpire(Date insuranceExpire) {
        this.insuranceExpire = insuranceExpire;
    }

    public Date getLicenceExpire() {
        return licenceExpire;
    }

    public void setLicenceExpire(Date licenceExpire) {
        this.licenceExpire = licenceExpire;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    
    
}
