/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class UOM extends BusObj {

    private static final long serialVersionUID = 1L;
    private String code;
    private String simbol;
//    @Enumerated(EnumType.ORDINAL)
    private byte type = UOMType.Other.getValue();// this can be primary ,carton, wholsale ..others
    private boolean isPrimary;

    public UOM() {
    
    super();
            }

    
    public UOM(String ID ,String code,double mult,byte type){
    super();    
    setCode(code);    
    setMulti(mult);
    setType(type);
            
    }
    public enum UOMType {

     Carton((byte) 0)   ,Primary((byte)1) , WholeSale((byte) 2), Other((byte) 3);
        byte value;

        UOMType() {
        }

        UOMType(byte bty) {
            value = bty;
        }
//http://stackoverflow.com/questions/2751733/map-enum-in-jpa-with-fixed-values

        public byte getValue() {
            return value;
        }

        @Override
        public String toString() {
            switch (this) {
                case Carton:
                    return "Carton";
                case Primary:
                    return "Primary";
                
                case Other:
                    return "Other";
                case WholeSale:
                    return "WholeSale";
            }

            return super.toString();
        }

        public static UOMType getUOMTypeForByte(byte b){
        switch(b){
        
            case 1:return Primary;
            case 0:return Carton;
            case 2:return WholeSale;
            case 3:return Other;
            default:return Primary;   
        }        
        }
        
        public static String valueForByte(byte v) {
            switch (v) {
                case 1:
                    return "Primary";
                case 0:
                    return "Carton";
                case 2:
                    return "WholeSale";
                case 3:
                    return "Other";
            }
            return "";

        }
    }

    public boolean isPrimary() {
        return type == UOMType.Primary.value;
    }
    
    public boolean isCarton() {
        return type == UOMType.Carton.value;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public byte getType() {
        return type;
    }

    public String getUOMType() {

        return UOMType.valueForByte(type);
    }

    public void setType(byte type) {
        this.type = type;
    }
    private String descriptiom;
    @OneToOne
    private UOM guom;
    private Double multi;
    private Double salesPrice;

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static UOM find(String code, List<UOM> lst) {


        Comparator<UOM> com = new Comparator<UOM>() {
            public int compare(UOM o1, UOM o2) {
                return o1.getId().toString().compareTo(o2.getId().toString());
            }
        };

        Collections.sort(lst, com);

        UOM s = new UOM();        
        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }


    public String getDescriptiom() {
        return descriptiom;
    }

    public void setDescriptiom(String descriptiom) {
        this.descriptiom = descriptiom;
    }

    public UOM getGuom() {
        return guom;
    }

    public void setGuom(UOM guom) {
        this.guom = guom;
    }

    public Double getMulti() {
        return multi;
    }

    public void setMulti(Double multi) {
        this.multi = multi;
    }

    public static void setUOMType(JComboBox cmb) {
        DefaultComboBoxModel cmbmo = new DefaultComboBoxModel();

        for (UOMType uOMType : UOMType.values()) {
            cmbmo.addElement(uOMType);
        }
        cmb.setModel(cmbmo);
//        
//        for (Object object : UOMType.values()) {
//            
//        }
//        switch(index){
//            case 0: return UOMType.Primary ;
//            case 1: return UOMType.Carton ;
//            case 2: return UOMType.WholeSale ;
//            case 3: return UOMType.Other;
//            default : return UOMType.Primary ;
//        }
    }

    public static UOMType getUOMType(int index) {
        //
        switch (index) {
            case 1:
                return UOMType.Primary;
            case 0:
                return UOMType.Carton;//carton
            case 2:
                return UOMType.WholeSale;
            case 3:
                return UOMType.Other;
            default:
                return UOMType.Primary;
        }
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
        if (!(object instanceof UOM)) {
            return false;
        }
        UOM other = (UOM) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.UOM[id=" + id + "]";

    }

   
}
