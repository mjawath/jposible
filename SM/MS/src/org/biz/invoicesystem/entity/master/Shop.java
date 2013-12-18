/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author nnjj
 */
@Entity
public class Shop extends BusObj{
   
    String code;
    String description;
    String contactNo;
    String address;
    
//may be many ware houses relative to this
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    public static Shop getDefaultShop(){
    Shop s= new Shop();
    s.setId("123");            
    s.setCode("123");            
    return s;
    }
}
