/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.controls;

import javax.persistence.Entity;
import org.biz.entity.BusObj;

/**
 *
 * @author jawa
 */
@Entity
public class MenuItem extends BusObj {
    
    private String parentMenu;//An object Menu typ will be nice
    private String actionClassName;
    private String screenClassName;
    private int type;
    private String menuName;
    private String description;
    private String iconName;

    public MenuItem() {
    }

    
    
    public MenuItem(String parentMenu, String actionClassName, int type, String menuName, String description) {
        this.parentMenu = parentMenu;
        this.actionClassName = actionClassName;
        this.type = type;
        this.menuName = menuName;
        this.description = description;
    }
    
    public MenuItem(String parentMenu, String actionClassName , String screenClassName, int type, String menuName, String description) {
        this(parentMenu,actionClassName,type,menuName,description);
        this.screenClassName =screenClassName;
               
    }
    
    
    
    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getActionClassName() {
        return actionClassName;
    }

    public void setActionClassName(String actionClassName) {
        this.actionClassName = actionClassName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getScreenClassName() {
        return screenClassName;
    }

    public void setScreenClassName(String screenClassName) {
        this.screenClassName = screenClassName;
    }
    
    
    
        @Override
    public String toString() {
        return "org.biz.invoicesystem.system.MenuItem[ id=" + id + " ]";
    }

    
}
