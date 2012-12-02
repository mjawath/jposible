/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.entity.accounts.bank;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author mjawath
 */
@Entity
public class Bank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String code;

    public void setCode(String code) {
        propertyChangeSupport.firePropertyChange("code", this.code , this.code = code);
    }

    public String getCode() {
        return code;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        propertyChangeSupport.firePropertyChange("id", this.id , this.id = id);
    }

  @Transient
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
