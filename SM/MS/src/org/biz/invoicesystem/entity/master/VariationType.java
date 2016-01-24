/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.entity.master;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.biz.entity.BusObj;

/**
 *
 * @author Administrator
 */
@Entity
public class VariationType extends BusObj{
    private static final long serialVersionUID = 1L;
    
    
    private String variationtype;//Size /Color / 
    @OneToMany
    private List<ItemAttribute> variations; 



}
