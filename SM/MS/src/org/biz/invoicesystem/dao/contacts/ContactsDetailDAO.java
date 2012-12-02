/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.dao.contacts;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.contacts.ContactDetail;

/**
 *
 * @author mjawath
 */
public class ContactsDetailDAO extends GenericDAO<ContactDetail>{

    
    public ContactsDetailDAO() {
    setCls(ContactDetail.class);
    }


}
