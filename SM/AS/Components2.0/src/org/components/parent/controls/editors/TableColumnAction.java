/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import javax.swing.JTable;

/**
 *
 * @author nnjj
 */
public class TableColumnAction{

    JTable tbl;

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public JTable getTbl() {
        return tbl;
    }
    
    public  int actionPerformed(){
        return -1;};
    
    
}