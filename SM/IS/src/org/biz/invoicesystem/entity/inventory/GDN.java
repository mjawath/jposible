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
public class GDN  extends BusObj  implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name="gdnid")
    @OneToMany(cascade=CascadeType.ALL)
    List<GDNLine> lines;

    public void setLines(List<GDNLine> lines) {
        this.lines = lines;
    }

    public List<GDNLine> getLines() {
        return lines;
    }



   
}
