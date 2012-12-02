package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 basically this class is for item variation like pendrive ..
 * can be varied by kingston imation...or else by 1gb  or 2gb or 8gb pendrives..
 */

@Entity
public class ItemVariation implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id; //default id for database..
    //private String code; //code for variation type this must be 
    private String description; //variation description...
  //  private String type;
    Double sPrice1; //saleprice one for variation
    Double sPrice2;//saleprice 2 for variation
    
//    @OneToMany
//    List<Variation> ivs;

    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVariation)) {
            return false;
        }
        ItemVariation other = (ItemVariation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.ItemVariation[id=" + id + "]";
    }

}
