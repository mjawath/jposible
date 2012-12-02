/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.contacts;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.contacts.ContactsDetailDAO;

/**
 *
 * @author mjawath
 */
public class ContactsDetailService extends Service {
    ContactsDetailDAO dao;

    public ContactsDetailService() {
    dao = new ContactsDetailDAO();
    }

    @Override
    public ContactsDetailDAO getDao() {
        return dao;
    }

}
