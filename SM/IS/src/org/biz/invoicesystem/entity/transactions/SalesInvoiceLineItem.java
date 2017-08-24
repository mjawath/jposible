/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.ItemBarcode;
import org.biz.invoicesystem.entity.master.SKU;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.util.MathUtil;

/**
 *
 * @author mjawath
 */
@Entity
@Table(name="LINEITEM")
public class SalesInvoiceLineItem extends BusObj  {
    
    private String description;
    @ManyToOne
    private Item item;//will be refectored with sku
    private Double qty;
    private String unit;
    private Double price;
    private Double lineAmount;
    private String itemMark;//this will be refectored with barcode
    
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private UOM uom;
    @ManyToOne
    private SKU sku;
    @ManyToOne(optional = true)   
    private ItemBarcode barcode;//
    
    

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
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

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    public ItemBarcode getBarcode() {
        return barcode;
    }

    public void setBarcode(ItemBarcode barcode) {
        this.barcode = barcode;
    }

    public boolean isValid() {
        if(qty>0 &&  price>0 && lineAmount > 0 && sku!=null)return true;
        return false;        
    }
    
}
