/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;

/**
 *
 * @author d
 */
@Entity
public class TransferOrderLineItem extends DocumentLineItemFacade {
    @OneToOne
    private Shop shopFrom;
    @OneToOne
    private Shop shopTo;
    @OneToOne
    private Warehouse wareHouseFrom;
    @OneToOne
    private Warehouse wareHouseTo;
    @OneToOne
    private Item item;
    @OneToOne
    private UOM uom;

    private String itemMark;
    private Double qty;

    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemMark() {
        return itemMark;
    }

    public void setItemMark(String itemMark) {
        this.itemMark = itemMark;
    }
    
    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Shop getShopFrom() {
        return shopFrom;
    }

    public void setShopFrom(Shop shopFrom) {
        this.shopFrom = shopFrom;
    }

    public Shop getShopTo() {
        return shopTo;
    }

    public void setShopTo(Shop shopTo) {
        this.shopTo = shopTo;
    }

    public Warehouse getWareHouseFrom() {
        return wareHouseFrom;
    }

    public void setWareHouseFrom(Warehouse wareHouseFrom) {
        this.wareHouseFrom = wareHouseFrom;
    }

    public Warehouse getWareHouseTo() {
        return wareHouseTo;
    }

    public void setWareHouseTo(Warehouse wareHouseTo) {
        this.wareHouseTo = wareHouseTo;
    }

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
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
        if (!(object instanceof TransferOrderLineItem)) {
            return false;
        }
        TransferOrderLineItem other = (TransferOrderLineItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.inventory.TransferOrderLineItem[ id=" + id + " ]";
    }
    
}
//happily we can remove shops, WHs from the line item