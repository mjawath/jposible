/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author Administrator
 */
@Entity
 
public class VehicleSale extends  BusObj implements Serializable {
  
    
    long Code;//this must be autonumber...for tracking purpose...
     
    
}
