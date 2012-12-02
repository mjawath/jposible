/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemPopUp.java
 *
 * Created on 16-Apr-2011, 22:41:19
 */
package org.biz.invoicesystem.master.ui;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;

/**
 *
 * @author mjawath
 */
public class UOMPopUp extends Popdialog {

    /** Creates new form ItemPopUp */
    public UOMPopUp() {
        super();
        initComponents();
        setTable(table);
//        setTextField(textField);
    }

    public UOMPopUp( JTextField field,List items) {
        initComponents();
        super.setTable(table);
        setSelectedColumn(1);
        init(field,items);
        populateTable();

    }
    public UOMPopUp( JComboBox field,List items) {
        initComponents();
        super.setTable(table);
        //super.setTextField(field);
        setItems(items);
        populateTable();
        setSelectedColumn(1);
        init(field);

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new org.components.controls.CxTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Code", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    @Override
    protected void setItem() {
        Object ob = TableUtil.getSelectedModelsValueAt(getTable(), 0);
        UOM i = UOM.find(ob.toString(), getItems());
        setSelectedItem(i);


    }

    @Override
    public void populateTable() {
        TableUtil.cleardata(getTable());
        for (Object ob : items) {
            UOM item=(UOM)ob;
                    Object[] row = {item.getId(),item.getSimbol()};
            TableUtil.addrow(getTable(), row);
        }

    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable table;
    // End of variables declaration//GEN-END:variables
}
