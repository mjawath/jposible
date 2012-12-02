
package org.biz.entity;
//bussiness entity object 

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Jawath
 */
@MappedSuperclass
public class BusObj implements Serializable{

    public BusObj() {
    }

    public BusObj(String id) {
        this.id = id;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id;
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    private Date saveddate;
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    Date editeddate;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
//     public Date getEditeddate() {
//        return editeddate;
//    }
//
//    public void setEditeddate(Date editeddate) {
//        this.editeddate = editeddate;
//    }
//
//    public Date getSaveddate() {
//        return saveddate;
//    }
//
//    public void setSaveddate(Date saveddate) {
//        this.saveddate = saveddate;
//    }
    
}
