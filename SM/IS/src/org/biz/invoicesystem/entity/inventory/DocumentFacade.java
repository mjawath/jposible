/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import java.util.List;
import javax.persistence.MappedSuperclass;
import org.biz.entity.BusObj;

/**
 *
 * @author d
 */
@MappedSuperclass
public abstract class DocumentFacade<E extends DocumentLineItemFacade> extends BusObj{



    public DocumentFacade() {
    initialiseList();
    }



    public void addToList(E ele) {
        getLineItemList().add(ele);
    }

    public void initialiseList() {

        if (getLineItemList()== null) {
//            setItemList( new ArrayList<E>());
            return;
        }
        if (getLineItemList().isEmpty()) {
            getLineItemList().clear();
        }

    }

    public E getLastItem(){
    return  getLineItemList().get(getLineItemList().size()-1);
    }

    public abstract List<E> getLineItemList();

    
    
}
