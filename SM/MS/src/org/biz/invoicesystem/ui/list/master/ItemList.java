/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.list.master;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingWorker;
import org.biz.app.ui.event.ButtonAction;
import org.biz.app.ui.util.ICommand;
import org.biz.dao.service.Service;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.ListViewPanel;



/**
 *
 * @author d
 */
public class ItemList extends ListViewPanel {

    private ItemService itemService;
    private List<Item> items;
    private ButtonAction actionX= new ButtonAction(){

        @Override
        public Object executeTask() {
            System.out.println("tell servie fuck the long taks    ");
            System.out.println("Exe   "+cTextField1.getText());
            items=service.getDao().getAll();
            return items;
        }

        @Override
        public void resultTask(Object objs) {
    
            System.out.println("Got result........"+items.size());
            tbl.setModelCollection(items);
            super.resultTask(objs);
       
        }        
    };
    
    /**
     * Creates new form ItemList
     */
    public ItemList() {
//        initComponents;
       super();
       initComponents();
    }

    @Override
    public void init() {
    super.init();    
    tbtn.addActionListener(actionX);
    }
    

    @Override
    public void setService(Service service) {
        super.setService(service);
        itemService =(ItemService) service;
        init();
        init(tbl);
        tbl.setModelClass(Item.class);        
        tbl.setPropertiesEL(new String[]{"id","code"});
        tbl.setColumnHeader(new String[]{"id","code"});
        tbl.setTableInteractionListner(new TableInteractionListner());
        
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

        searchPanel = new javax.swing.JPanel();
        tbtn = new org.components.controls.CButton();
        cTextField1 = new org.components.controls.CTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new org.components.controls.CxTable();

        tbtn.setText("Find");
        tbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnActionPerformed(evt);
            }
        });

        cTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(tbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(tbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                    .addGap(13, 13, 13)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 365, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnActionPerformed

        

    }//GEN-LAST:event_tbtnActionPerformed

    private void cTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField1ActionPerformed
            
       List list= itemService.getDao().getAll();        
       tbl.setModelCollection(list); 
    }//GEN-LAST:event_cTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cTextField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel searchPanel;
    protected org.components.controls.CxTable tbl;
    private org.components.controls.CButton tbtn;
    // End of variables declaration//GEN-END:variables
}
