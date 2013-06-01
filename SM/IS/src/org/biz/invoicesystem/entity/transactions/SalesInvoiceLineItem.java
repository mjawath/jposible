/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

import app.utils.MathUtil;
import org.biz.entity.BusObj;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;

/**
 *
 * @author mjawath
 */
@Entity
public class SalesInvoiceLineItem extends BusObj implements Serializable  {
    
    private String description;
    @OneToOne
    private Item item;
    private Double qty;
    private String unit;
    private Double price;
    private Double lineAmount;
    private String itemMark;
    
    @OneToOne
    Warehouse warehouse;
    @OneToOne
    Shop shop;
    @OneToOne
    UOM uom;
    
    

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }
    
    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    

    public void setLineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

    public Double getPrice() {
        return price;
    }

    public Double getLineAmount() {
        return lineAmount;
    }

    public String getDescription() {
        return description;
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

    public String getItemMark() {
        return itemMark;
    }

    public void setItemMark(String itemMark) {
        this.itemMark = itemMark;
    }

    public Double getSalesPrice() {
        if(unit!=null){
        if(unit.equals(item.getUnitOne()))
        setPrice(item.getUnit1SalesPrice());
        
        if(unit.equals(item.getUnitTwo()))
        setPrice(item.getUnit2SalesPrice());
        
        }
        
        return price;
    }

    public void calculateLineItem(){    
        setLineAmount(MathUtil.multiply(getQty(), getPrice()));    
    }
    
}
