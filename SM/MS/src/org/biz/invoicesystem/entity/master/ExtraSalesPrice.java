package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *this is for wholesale needs and no practical usages..only shiwing the 
 * prices for price ranges..like 10-100 pcs 50Rs and 10 -500 pcs 45 Rs
 * 
 */
@Entity
public class ExtraSalesPrice implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String Description;  //description like prices for 10-100 pcs ...
    double price; //how much for 10-100 pcs ? 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
}
