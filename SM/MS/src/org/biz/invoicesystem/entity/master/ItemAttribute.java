package org.biz.invoicesystem.entity.master;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.biz.entity.BusObj;

 
@Entity
public class ItemAttribute extends BusObj{
        
    @OneToOne
    private  VariationType variationType;
    private String attributeValue;

}
