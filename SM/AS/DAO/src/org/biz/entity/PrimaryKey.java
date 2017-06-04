/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author jawa
 */
@Embeddable
public class PrimaryKey implements Serializable {
    
   
    private Long id;
    private Long genClass;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrimaryKey other = (PrimaryKey) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
