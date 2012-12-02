/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author Administrator
 */
@Entity
public class RoleTasks implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
  private String formname;
    private String specialfields;
 Integer accessLevel;
  private Boolean isSentToMaster;
 private Boolean isDeleted;

 @Temporal(TemporalType.DATE)
   Date crtdTime;
   
}
