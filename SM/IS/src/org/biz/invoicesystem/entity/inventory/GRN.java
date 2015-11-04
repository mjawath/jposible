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

  

    public List<GRNLine> getLines() {
        return lines;
    }

    public void setLines(List<GRNLine> lines) {
        this.lines = lines;
    }
    

   
    
}
