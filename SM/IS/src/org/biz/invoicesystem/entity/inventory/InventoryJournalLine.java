/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;

/**
 *
 * @author mjawath
 */
@Entity
public class InventoryJournalLine  extends BusObj implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private String description;
    @OneToOne
    private Item item;
    private Double qty;
    private String unit;
    @OneToOne
    private Shop shop;
    @OneToOne
    private Warehouse warehouse;
    private String itemMark;    
    @OneToOne
    private UOM uom;
    private Double price;
    private Double lineAmount;

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public UOM getUom() {
        return uom;
    }
    public String getUOMCode(){
    return uom==null?"":uom.getCode();
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    public String getItemMark() {
        return itemMark;
    }

    public void setItemMark(String itemMark) {
        this.itemMark = itemMark;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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
        if (!(object instanceof InventoryJournalLine)) {
            return false;
        }
        InventoryJournalLine other = (InventoryJournalLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
       
    public boolean isValidLine() {
        
        if (getItem() == null || getUom() == null || getQty() == null || getQty()==0) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.InventoryJournal[id=" + id + "]";
    }

    public void calculateLineItem() {
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
