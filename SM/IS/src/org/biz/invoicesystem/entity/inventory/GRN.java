/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.entity.inventory;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.biz.entity.BusObj;

/**
 *
 * @author Administrator
 */
@Entity
public class GRN extends BusObj implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name="grnid")
    @OneToMany(cascade=CascadeType.ALL)
    List<GRNLine> lines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GRNLine> getLines() {
        return lines;
    }

    public void setLines(List<GRNLine> lines) {
        this.lines = lines;
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
        if (!(object instanceof GRN)) {
            return false;
        }
        GRN other = (GRN) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.inventory.GRN[id=" + id + "]";
    }

}
