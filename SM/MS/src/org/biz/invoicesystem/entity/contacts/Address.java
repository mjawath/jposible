/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.contacts;

import java.io.Serializable;
import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class Address  extends BusObj  implements Serializable {

    private static final long serialVersionUID = 1L;
    private String homeNo;
    private String street1;
    private String street2;
    private String street3;

    public Address() {
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }


    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    public String getHomeNo() {
        return homeNo;
    }

   
    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getStreet3() {
        return street3;
    }


}
