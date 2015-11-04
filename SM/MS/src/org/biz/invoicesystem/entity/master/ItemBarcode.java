package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author Administrator
 */
@Entity
public class ItemBarcode extends BusObj implements Serializable {

    private static final long serialVersionUID = 1L;
    
    String type; ///item type for the barcode..color changes,quality changes..
    String barcode;

    public ItemBarcode() {
    }

    public ItemBarcode(String type, String barcode) {
        this.type = type;
        this.barcode = barcode;
    }

    
    
    
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
