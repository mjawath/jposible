package org.biz.entity;
//bussiness entity object 

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author Jawath
 */
@MappedSuperclass
public class BusObj implements Serializable {

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)//these auditable datas can be put to emeded entity as one one relationship
    private Date editedDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date savedDate;

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

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editeddate) {
        this.editedDate = editeddate;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date saveddate) {
        this.savedDate = saveddate;
    }
}
