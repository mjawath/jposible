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
//                        if(gridLine.getGridContainer().is)
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
//        jScrollPane2.getVerticalScrollBar().setUnitIncrement(80);
    }
        
    private void addNewToTable(){                 
        if (selectedLine != null) {
            selectedLine.UIToData();
            if (!selectedLine.isLineItemValid()) {
                System.out.println("line item not valid");
                return;
            }
        }//validate previoue selected line

        final GridDataLineDetailUI com = getItemAdder().getLineUI(null);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
        Object obj = com.getLineObject();        
        listBusObject.add(obj);
        com.containerUI = this;
        container.add(com);
        listLinesUI.add(com);
        addToFocus(com);
        repaint();
        revalidate();
    }
    



    
    public void addNewLineGotoNewLine(){        
            addNewToTable();
            goToNextRowFirstCom();        
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
    
    public void clearUI(){
        container.removeAll();
        listBusObject= new ArrayList() ;
        listLinesUI=  new ArrayList<>();
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
    
    public void setCollection(List data){
        /**
         if any of the line items related line ui has the focus then there is no point of setting data to this panel
         */
        clearUI();
        
        for (Object data1 : data) {
            GridDataLineDetailUI com = getItemAdder().getLineUI(data1);
            Object obj = com.getLineObject();
            listBusObject.add(obj);
            com.containerUI = this;
            container.add(com);
            listLinesUI.add(com);
            addToFocus(com);
            
        }
        repaint();
        revalidate();

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
        btnClearLine = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        btnMoveUp = new javax.swing.JButton();
        btnMoveDown = new javax.swing.JButton();

        container.setLayout(new javax.swing.BoxLayout(container, javax.swing.BoxLayout.Y_AXIS));
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

        btnClearLine.setText("Clear Line");
        btnClearLine.setFocusable(false);

        btnClearAll.setText("Clear All");
        btnClearAll.setFocusable(false);

        btnMoveUp.setText("Move Up");
        btnMoveUp.setFocusable(false);

        btnMoveDown.setText("Move Down");
        btnMoveDown.setFocusable(false);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoveUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoveDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveDown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearLine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearAll)
                .addContainerGap(69, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnClearLine, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnClearAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnMoveUp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnMoveDown, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(selectedLine!=null){
            onRemovLineItem(selectedLine);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addNewToTable();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnClearLine;
    private javax.swing.JButton btnMoveDown;
    private javax.swing.JButton btnMoveUp;
    private javax.swing.JPanel container;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
