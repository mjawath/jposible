/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz;

import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.WorkStation;
import org.components.util.Sessions;
import org.util.PropertyUtil;

/**
 *
 * @author d
 */
public class MS_Static {

    public static Integer getSalesInvoiceLineDetailUI() {
        Integer property = PropertyUtil.getProperty("SalesInvoiceLineDetailUI");
        if (property == null) {
            return 0;//default is 0
        }
        return property;
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
    
    private static WorkStation defaultWorkStation;

    public static WorkStation getDefaultWorkStation() {
        return defaultWorkStation;
    }

    public static void setDefaultWorkStation(WorkStation defaultWorkStation) {
        MS_Static.defaultWorkStation = defaultWorkStation;
    }
    
    public static String getDefaultWorkStationCode() {
        String property = PropertyUtil.getProperty("WorkStation");
        if (property == null) {
            return null;//default is 0
        }
        return property;

    }
    
    public static Staff getLoggedInStaff(){
        return (Staff) Sessions.getObj("user");
    }
    
    public static void setLoggedInStaff(Staff staff){
        Sessions.addToSession("user", staff);
    }

}
