/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.prototype;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import org.components.containers.CPanel;

/**
 *
 * @author user
 */
public class GridDataContainerUI extends CPanel implements GridDataContainer{

    private LineItemAdder itemAdder;
    private List listBusObject = new ArrayList(); 
    private List<GridDataLineDetailUI> listLinesUI = new ArrayList(); 
    private GridDataLineDetailUI selectedLine;
    
    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener() {
            GridDataLineDetailUI obj2;

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if (!(evt.getNewValue() instanceof Component)) {
                    return;
                }
                if ("focusOwner".equals(evt.getPropertyName())) {

                    Object obj = SwingUtilities.getAncestorOfClass(GridDataLineDetailUI.class, (Component) evt.getNewValue());
                    if (!(obj instanceof GridDataLineDetailUI) && obj2 != null) {
                        GridDataLineDetailUI gridLine = (GridDataLineDetailUI) obj2;
                        gridLine.setBorder(null);
                        gridLine.getGridContainer().setSelectedLine(null);
                        obj2 = null;
                        return;
                    } else if (obj instanceof GridDataLineDetailUI) {
                        GridDataLineDetailUI gridLine = (GridDataLineDetailUI) obj;

                        if (gridLine.getGridContainer().getSelected() != gridLine) {

                            if (gridLine.getGridContainer().getSelected() != null) {
                                gridLine.getGridContainer().getSelected().setBorder(null);
                            }
                            ((GridDataLineDetailUI) obj).getGridContainer().setSelectedLine((GridDataLineDetailUI) obj);
                            gridLine = (GridDataLineDetailUI) obj;
                            gridLine.setBorder(BorderFactory.createLineBorder(Color.red));
                            obj2 = gridLine;
                            return;
                        }
                    }

                }
            }
        });

    }
    /**
     * Creates new form GridDataContainerUI
     */
    public GridDataContainerUI() {
        initComponents();
    }
        
    public void addNewToTable(){
        final GridDataLineDetailUI com = getItemAdder().getLineUI();
        Object obj = com.getLineObject();
        listBusObject.add(obj);
        com.containerUI = this;
        container.add(com);
        listLinesUI.add(com);
        addToFocus(com);
        repaint();
        revalidate();
    }
    
    public Component goToNextRowFirstComOrCreateNew() {
        Component gld = goToNextRowFirstCom();
        if(gld==null){
            addNewToTable();
            return goToNextRowFirstCom();
        }else{
            return gld;
        }        
    }
    
    public Component goToNextRowFirstCom() {
        GridDataLineDetailUI gld = getNextRow();
        if (gld != null) {
            return gld.gotoFirstComponent();
        }
        return null;
    }

    public GridDataLineDetailUI getNextRow() {
        GridDataLineDetailUI gld = getSelected();

        int x = 0;
        for (GridDataLineDetailUI com : listLinesUI) {

            if (com == gld) {
                if(x +1 < listLinesUI.size() )
                return (GridDataLineDetailUI) listLinesUI.get(++x);

            }
            x++;
        }
        return null;
    }
    
    public void onRemovLineItem( GridDataLineDetailUI line){        
        getItemAdder().removeLine(line);        
    }

    public void removLineItem(GridDataLineDetailUI line) {
        container.remove(line);        
        listBusObject.remove(line.getLineObject());
        listLinesUI.remove(line);
        focus.remove(line);
        revalidate();
        repaint();
    }
    
    public void setSelectedLine(GridDataLineDetailUI dline){
        selectedLine = dline;
        
    }
    
    public GridDataLineDetailUI getSelected(){
    return selectedLine;
    }
    /**
     * @return the itemAdder
     */
    public LineItemAdder getItemAdder() {
        return itemAdder;
    }

    public List getlistBusObject() {
        return listBusObject;
    }

    /**
     * @param itemAdder the itemAdder to set
     */
    public void setItemAdder(LineItemAdder itemAdder) {
        this.itemAdder = itemAdder;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        container = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        container.setLayout(new java.awt.GridLayout(200, 0));
        jScrollPane2.setViewportView(container);

        jButton1.setText("Add");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.setFocusable(false);

        jButton4.setText("Copy");
        jButton4.setFocusable(false);

        jButton5.setText("Clear");
        jButton5.setFocusable(false);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(41, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLayeredPane2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addNewToTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        if(selectedLine!=null){
            onRemovLineItem(selectedLine);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
