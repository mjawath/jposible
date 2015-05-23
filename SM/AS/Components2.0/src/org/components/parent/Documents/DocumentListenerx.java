/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.Documents;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author yy
 */
public class DocumentListenerx  implements DocumentListener{

    
    
    @Override
    public void insertUpdate(DocumentEvent e) {       
        action(e); 
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       action(e); 
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
       action(e); 
    }
    
    public void action(DocumentEvent e ){
        
    }
    
}
