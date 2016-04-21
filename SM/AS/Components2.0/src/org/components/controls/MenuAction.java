/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

import com.components.custom.ActionTask;
import java.awt.event.ActionEvent;
import org.biz.app.ui.util.StringUtility;
import org.components.util.Sessions;
import org.components.windows.UIController;

/**
 *
 * @author jawa
 */
public class MenuAction extends ActionTask{

    public MenuAction(Object obj) {
        super();
    
    }   

    
    public void actionCall(Object obj) {
        if(obj instanceof ActionEvent  && ((ActionEvent) obj).getSource() instanceof MenuItemComponent ){
            final MenuItemComponent menu = (MenuItemComponent)((ActionEvent) obj).getSource();
            if( menu.getBusObj() instanceof MenuItem){
                final MenuItem menuItem = (MenuItem)menu.getBusObj();
                menuItem.getScreenClassName();                
                //check whther object already registered                
                UIController cont = Sessions.getControllerOrCreateNew(menuItem.getActionClassName());
                if (cont == null) {
                    return;
                }
                
                if(!StringUtility.isEmptyString(menuItem.getScreenClassName())){
                cont.showFrame(menuItem.getScreenClassName());                
                }else if (menuItem.getType()>0) {
                    cont.showFrame(menuItem.getType());
                }else{
                    cont.showFrame();
                }
            }
            
                        //check whther object already registered

//        Sessions.addToSession(String.valueOf(obj), ui);            
        }
        
        
    }
    
    
     
    
    
}
