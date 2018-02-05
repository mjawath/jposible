/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author LENOVO PC
 */
@Entity
public class WorkStation extends BusObj{
    
    private String name;
    private String code;
    private String description;

    public WorkStation() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
