/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.components.parent.controls;

import java.util.Formatter;
import javax.swing.table.TableColumn;

/**
 *
 * @author jawa
 */
public class PTableColumn extends TableColumn{
    private Class classType;
    private String title;
    private Formatter formatter;
    private int columnWidth;

    public PTableColumn(Class classType, String title, Formatter formatter,int colWidht) {
        super();
        this.classType = classType;
        this.title = title;
        this.formatter = formatter;
        this.columnWidth  = colWidht;
    }

    public PTableColumn(Class classType, String title, Formatter formatter) {
        this(classType,title,formatter,75);
    }
    
    public PTableColumn(Class classType, String title) {
        this(classType,title,null,75);
    }
   
    
    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
    
    
    
}
