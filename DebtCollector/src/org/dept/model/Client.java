/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dept.model;

import org.biz.entity.BusObj;

/**
 *
 * @author d
 */
public class Client extends BusObj{
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client(String id,String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Client() {
    super();
    }
    
    
}
