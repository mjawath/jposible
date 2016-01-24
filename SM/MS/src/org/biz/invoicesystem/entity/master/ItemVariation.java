package org.biz.invoicesystem.entity.master;

import javax.persistence.Entity;
import org.biz.entity.BusObj;
 
/*
 basically this class is for item variation like pendrive ..
 * can be varied by kingston imation...or else by 1gb  or 2gb or 8gb pendrives..
 */

@Entity
public class ItemVariation extends BusObj{
    private String description; //variation description...
  //  private String type;
    Double sPrice1; //saleprice one for variation
    Double sPrice2;//saleprice 2 for variation
  
    private String variationKey;
    private String []values;
    
//    @OneToMany
//    List<Variation> ivs;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getsPrice1() {
        return sPrice1;
    }

    public void setsPrice1(Double sPrice1) {
        this.sPrice1 = sPrice1;
    }

    public Double getsPrice2() {
        return sPrice2;
    }

    public void setsPrice2(Double sPrice2) {
        this.sPrice2 = sPrice2;
    }

}
