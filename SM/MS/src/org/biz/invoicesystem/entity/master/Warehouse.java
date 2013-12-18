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
public class Warehouse extends BusObj {
    String code;
    
//may have many shops relative to this
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
/**
 * defining hirachical structure for the location 
 * 
 * location 
 *      location 
 *          rack 
 *              bin
 * Tree structure 
 * 
 * 
 * 
 */