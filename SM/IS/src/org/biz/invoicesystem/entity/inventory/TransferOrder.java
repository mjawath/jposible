
package org.biz.invoicesystem.entity.inventory;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;

/**
 *
 * @author jawath
 */
@Entity
public class TransferOrder extends DocumentFacade<TransferOrderLineItem> {
    @OneToOne
    private Shop shopFrom;
    @OneToOne
    private Shop shopTo;
    @OneToOne
    private Warehouse wareHouseFrom;
    @OneToOne
    private Warehouse wareHouseTo;
    @JoinColumn(name = "transfer_order_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch= FetchType.LAZY)
    private List<TransferOrderLineItem> lineItem;

    public TransferOrder() {
    lineItem = new ArrayList<TransferOrderLineItem>();
    }




    public Shop getShopFrom() {
        return shopFrom;
    }

    public void setShopFrom(Shop shopFrom) {
        this.shopFrom = shopFrom;
    }

    public Shop getShopTo() {
        return shopTo;
    }

    public void setShopTo(Shop shopTo) {
        this.shopTo = shopTo;
    }

    public Warehouse getWareHouseFrom() {
        return wareHouseFrom;
    }

    public void setWareHouseFrom(Warehouse wareHouseFrom) {
        this.wareHouseFrom = wareHouseFrom;
    }

    public Warehouse getWareHouseTo() {
        return wareHouseTo;
    }

    public void setWareHouseTo(Warehouse wareHouseTo) {
        this.wareHouseTo = wareHouseTo;
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
        if (!(object instanceof TransferOrder)) {
            return false;
        }
        TransferOrder other = (TransferOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.inventory.TransferOrder[ id=" + id + " ]";
    }


    public List<TransferOrderLineItem> getLineItemList() {
        return lineItem;
    }
  

}
