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
import org.biz.invoicesystem.entity.master.UOM;

/**
 *
 * @author Administrator
 */
@Entity
public class GDNLine extends BusObj  implements Serializable {
    private static final long serialVersionUID = 1L;
    

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

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }
    private String description;
    @OneToOne
    private Item item;
    private Long qty;
    @OneToOne
    private UOM uom;
    private Double price;
    private Double lineAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

}
