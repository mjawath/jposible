/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author mjawath
 */
@Entity
public class UOM implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String simbol;
//    @Enumerated(EnumType.ORDINAL)
    private Byte type;// this can be primary ,carton, wholsale ..others
    private Boolean isPrimary;

    public enum UOMType {

        Primary, Carton, WholeSale, Other;
        byte value;

        UOMType() {
        }
//http://stackoverflow.com/questions/2751733/map-enum-in-jpa-with-fixed-values


         UOMType(byte vl) {
            value = vl;
        }

        public byte getValue() {
            return value;
        }

        @Override
        public String toString() {
            switch (this) {
                case Primary:
                    return "Primary";
                case Carton:
                    return "Carton";
                case Other:
                    return "Other";
                case WholeSale:
                    return "WholeSale";
            }

            return super.toString();
        }
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
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

    public static UOM find(String code, List<UOM> lst) {


        Comparator<UOM> com = new Comparator<UOM>() {

            public int compare(UOM o1, UOM o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };

        Collections.sort(lst, com);

        UOM s = new UOM();
        s.setId(code);
        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
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
            case 0:
                return UOMType.Primary;
            case 1:
                return UOMType.Carton;
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
