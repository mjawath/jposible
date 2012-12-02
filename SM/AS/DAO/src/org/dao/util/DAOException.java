/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao.util;

/**
 *
 * @author nnjj
 */
public class DAOException extends RuntimeException{

    public DAOException(Exception e) {
        e.printStackTrace();
    }
    
}
