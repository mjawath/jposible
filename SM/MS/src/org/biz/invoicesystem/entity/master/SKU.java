/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.biz.entity.BusObj;

/**
 *
 * @author jawa
 */
@Entity
public class SKU extends BusObj{//Stock keeping unit
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;    
    private String code;//can be a barcode barcode
    private String explainningSearchString;    
    
    private List<ItemAttribute> itemAttributes;
       
    private List<ItemBarcode> barcodes;//as a convention first barcode is the default and that is same as sku code 
        

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;        
    }
    
 
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExplainningSearchString() {
        return explainningSearchString;
    }

    public void setExplainningSearchString(String explainningSearchString) {
        this.explainningSearchString = explainningSearchString;
    }

    public void addSKUorUpdate(ItemAttribute attribute) {
        

        if (itemAttributes == null) {
            itemAttributes = new ArrayList<ItemAttribute>();            
            itemAttributes.add(attribute);
            return;
        }
        if (attribute.getId() == null) {

            itemAttributes.add(attribute);
            return;
        }

        int it = -1;
        for (ItemAttribute item : itemAttributes) {
            it++;
            if (attribute.getId() != null && attribute.getId().equals(item.getId())) {
//                 item=uom;//replace item
                itemAttributes.set(it, attribute);
                if (it == 0) {
                    
                }
                return;
            }
        }

//        if(it>0){  uoms.set(it, uom);return;}
    }

    
    
    
    
    
    
}
