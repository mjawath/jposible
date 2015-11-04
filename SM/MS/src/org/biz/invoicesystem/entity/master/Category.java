/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class Category  extends BusObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private String description;
    private String inventoryType;
    @ManyToOne(cascade=CascadeType.ALL)
    private UOM unitOne;
    @ManyToOne(cascade=CascadeType.ALL)
    private UOM unitTwo;


public static Category find(String code, List<Category> lst) {


        Comparator<Category> com = new Comparator<Category>() {

            public int compare(Category o1, Category o2) {
                return o1.getId().toString().compareTo(o2.getId().toString());
            }
        };

        Collections.sort(lst, com);

        Category s = new Category();
        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }
 

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public void setUnitOne(UOM unitOne) {
        this.unitOne = unitOne;
    }

    public void setUnitTwo(UOM unitTwo) {
        this.unitTwo = unitTwo;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public UOM getUnitOne() {
        return unitOne;
    }

    public UOM getUnitTwo() {
        return unitTwo;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.master.item.Item[id=" + id + "]";
    }

}
