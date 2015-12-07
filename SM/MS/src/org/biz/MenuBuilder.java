/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz;

import org.components.controls.MenuItem;
import app.utils.SystemUtil;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import org.biz.app.ui.util.StringUtility;
import org.components.controls.Menu;
import org.components.controls.MenuAction;
import org.components.controls.MenuItemComponent;

/**
 *
 * @author jawa
 */
public class MenuBuilder {
   
   private  final  HashMap<String,Class<Action>> menu = new HashMap();   
   
   
   public void addMenu(String menuName,Class<Action> menuActionClass){
       //get the menu hierachi from propfile of db
       
       menu.put(menuName, menuActionClass);       
   }
   public void createMenuItem(MenuItem menuItem) {
       JMenuBar mb = SystemUtil.getMenuBar();
       if(StringUtility.isEmptyString(menuItem.getParentMenu()))return;
       String []arra =    menuItem.getParentMenu().split(">");
       
       JComponent parentCom = mb;
       for (int i = 0; i < arra.length; i++) {
           String menuName = arra[i];
           parentCom = createMenuIfNot(menuName, parentCom);
       }
       
       if(parentCom!=null && parentCom  instanceof Menu){
           MenuItemComponent mic = new MenuItemComponent();
           MenuAction ma = new MenuAction(menuItem.getActionClassName());
//           ma.set
           mic.addActionListener(ma);
           mic.setText(menuItem.getMenuName());
           mic.setBusObj(menuItem);
           parentCom.add(mic);
       }

    }
   private JComponent createMenuIfNot(String menuName,JComponent parent){
       int componentCount =0;
       if(parent instanceof JMenuBar){
           componentCount = parent.getComponentCount();
       }else if (parent instanceof JMenu) {
           componentCount = ((Menu) parent).getMenuComponentCount();
       }
       
       

       if(componentCount==0){
           return createNewMenuAddToParent(menuName, parent);
       }
       
       for (int i = 0; i < componentCount; i++) {
           Object menuObj = null;
           if (parent instanceof JMenuBar) {
            menuObj= parent.getComponent(i);
           }else if (parent instanceof JMenu) {
               menuObj = ((JMenu)parent).getMenuComponent(i);
           }
           if (!(menuObj instanceof Menu)){
               continue;
           }
               if (StringUtility.isSameString(((Menu) menuObj).getText(), menuName)) {
                   return (Menu) menuObj;
               }
//               else {
//                   return createNewMenuAddToParent(menuName, parent);
//               }

       }
       return createNewMenuAddToParent(menuName, parent);
   }
   private JComponent createNewMenuAddToParent(String menuName,JComponent parent){
       Menu menu = new Menu();
       menu.setText(menuName);
       parent.add(menu);
       return menu;
   }
}
