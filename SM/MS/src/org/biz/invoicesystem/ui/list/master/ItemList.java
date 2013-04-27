/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.util.List;
import javax.swing.SwingWorker;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;

/**
 *
 * @author d
 */
public class ItemList extends ListViewUI {

    ItemService itemService;
    /**
     * Creates new form ItemList
     */
    public ItemList() {
        initComponents();
        itemService = new ItemService();
        tbl.setModelClass(Item.class);
        tbl.setPropertiesEL(new String[]{"id","code"});
        tbl.setColumnHeader(new String[]{"id","code"});
    }

    @Override
    public void updateEntityUI() {

        new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                List<Item> list = itemService.getDao().getAll();
                System.out.println("fillin with list ");
                return list;
            }

            @Override
            protected void done() {
                try {
                    tbl.setModelCollection((List) get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.execute();

        super.updateEntityUI(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();

        jScrollPane1.setViewportView(tbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addGap(13, 13, 13)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tbl;
    // End of variables declaration//GEN-END:variables
}
