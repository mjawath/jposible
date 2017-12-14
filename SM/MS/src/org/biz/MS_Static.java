/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz;

import javax.swing.JTabbedPane;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;
import org.util.PropertyUtil;

/**
 *
 * @author d
 */
public class MS_Static {
    
        public static Integer getSalesInvoiceLineDetailUI(){
        Integer property = PropertyUtil.getProperty("SalesInvoiceLineDetailUI");
        if(property==null)return 0;//default is 0
        return  property;
    }
    public static String getDefaultWareHouseCode() {
        String property = PropertyUtil.getProperty("WareHouse");
        if (property == null) {
            return null;//default is 0
        }
        return property;

    }
    public static String getDefaultShopCode() {
        String property = PropertyUtil.getProperty("Shop");
        if (property == null) {
            return null;//default is 0
        }
        return property;

    }
    
    private static Warehouse defaultWareHouse;

    public static Warehouse getDefaultWareHouse() {
        return defaultWareHouse;
    }

    public static void setDefaultWareHouse(Warehouse warehouse) {
        defaultWareHouse = warehouse;
    }

    private static Shop defaultShop;

    public static Shop getDefaultShop() {
        return defaultShop;
    }

    public static void setDefaultShop(Shop v) {
        defaultShop = v;

    }

}
