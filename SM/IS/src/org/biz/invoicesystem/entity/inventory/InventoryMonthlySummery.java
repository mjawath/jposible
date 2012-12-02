/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.entity.inventory;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;

/**
 *
 * @author mjawath
 */
@Entity
public class InventoryMonthlySummery extends BusObj  implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private String code;
    private Long qty;
    @ManyToOne
    private Item item;
    @ManyToOne
    private UOM unitOne;
    private Long qty1;
    @ManyToOne
    private UOM unitTwo;
    private Long qty2;
    private Integer monthnum;
    private Long salesInvoice;
    private Long salesOrder;
    private Long purchaseInvoice;
    private Long purchaseOrder;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date summeriesedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entryDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getQty1() {
        return qty1;
    }

    public void setQty1(Long qty1) {
        this.qty1 = qty1;
    }

    public Long getQty2() {
        return qty2;
    }

    public void setQty2(Long qty2) {
        this.qty2 = qty2;
    }

    public UOM getUnitOne() {
        return unitOne;
    }

    public void setUnitOne(UOM unitOne) {
        this.unitOne = unitOne;
    }

    public UOM getUnitTwo() {
        return unitTwo;
    }

    public void setUnitTwo(UOM unitTwo) {
        this.unitTwo = unitTwo;
    }

    public Integer getMonthnum() {
        return monthnum;
    }

    public void setMonthnum(Integer monthnum) {
        this.monthnum = monthnum;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Long getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(Long purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
    }

    public Long getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Long purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Long getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(Long salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public Long getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(Long salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryMonthlySummery)) {
            return false;
        }
        InventoryMonthlySummery other = (InventoryMonthlySummery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.InventoryJournal[id=" + id + "]";
    }

}
