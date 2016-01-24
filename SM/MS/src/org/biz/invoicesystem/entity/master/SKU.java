/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.biz.entity.BusObj;

/**
 *
 * @author jawa
 */
@Entity
public class SKU extends BusObj{//Stock keeping unit
    
    
    @ManyToOne
    private Item item;    
    private String code;//can be a barcode barcode
    private String explainningSearchString;    
    
    private List<ItemAttribute> itemAttributes;
    
    private List<UOM> uoms;//normal sinario this referes to the items uoms
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

    public void addSKUorUpdate(ItemAttribute uom) {
        

        if (uoms == null) {
            itemAttributes = new ArrayList<ItemAttribute>();            
            itemAttributes.add(uom);
            return;
        }
        if (uom.getId() == null) {

            itemAttributes.add(uom);
            return;
        }

        int it = -1;
        for (ItemAttribute item : itemAttributes) {
            it++;
            if (uom.getId() != null && uom.getId().equals(item.getId())) {
//                 item=uom;//replace item
                itemAttributes.set(it, uom);
                if (it == 0) {
                    
                }
                return;
            }
        }

//        if(it>0){  uoms.set(it, uom);return;}
    }

    
    
    
    
    
    
}
