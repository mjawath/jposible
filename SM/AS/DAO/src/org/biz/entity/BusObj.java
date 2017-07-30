package org.biz.entity;
//bussiness entity object 

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Jawath
 */
//@IdClass(value = PrimaryKey.class)
@MappedSuperclass
public class BusObj implements Serializable {

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)//these auditable datas can be put to emeded entity as one one relationship
    private Date editedDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date savedDate;

    public BusObj() {
    }

    public BusObj(PrimaryKey id) {
//        this.id = id;
    }
    @Id
    @UuidGenerator(name="UUID")
    @GeneratedValue(generator="UUID")
    protected String id;
//    @Id    
//    private Long genClass;

    
    
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

    public void setId(PrimaryKey id) {
//        this.id = id;
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

    public void setDepententEntitiesIDs() {
    //sets the ids to the onetomany, manyto many

    }

    public void setLineID(List< ? extends BusObj> obj) {
        int x = 100;
        for (BusObj busObj : obj) {
//            busObj.setId(getId()+ x++);
        }
    }
}
