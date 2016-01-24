/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package research.prototype.transaction;

import com.components.custom.TextFieldWithPopUP;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.ui.list.master.ItemLV;

/**
 *
 * @author Jawad
 */
public class ItemPopup extends TextFieldWithPopUP<Item> {

           /**
     * Creates new form ItemPopup
     */
    public ItemPopup() {
//        initComponents();?
        super(); 
//        initPopup(Item.class, new Class[]{Item.class,String.class,String.class},new String[]{"Code","Desc"} ,"code",this);
      
        setListView(new ItemLV());
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


}
