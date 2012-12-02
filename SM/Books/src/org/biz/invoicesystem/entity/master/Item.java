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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author mjawath
 */
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String code;
    private String name;
    @OneToOne
    private Product product;
    @OneToOne
    private ItemVariation variation;
    private String description;
    private String inventoryType;
    @ManyToOne(cascade=CascadeType.ALL)
    private UOM unitOne;
    @ManyToOne(cascade=CascadeType.ALL)
    private UOM unitTwo;
    //item has many mariation
    //variation has name value --colur = red/colur = yellow , size = 15 ,size = 20 
    

    public static Item find(String code, List<Item> lst) {


        Comparator<Item> com = new Comparator<Item>() {

            public int compare(Item o1, Item o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };

        Collections.sort(lst, com);

        Item s = new Item();
        s.setId(code);
        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
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
