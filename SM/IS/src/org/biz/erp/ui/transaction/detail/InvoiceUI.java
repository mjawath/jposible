/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.erp.ui.transaction.detail;

import com.components.custom.PopupListner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.parent.controls.editors.DoubleCellEditor;
import org.components.parent.controls.editors.ObjectCellEditor;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.DetailPanel;


/**
 *
 * @author d
 */
public class InvoiceUI extends DetailPanel<SalesInvoice> {

    
    List<Item> items;
     class MyKey extends KeyAdapter implements ICommand {

        Command com = new Command(this);

        public MyKey() {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            com.setParam("event param ");
            com.setView("event param ");
            com.invoke();
        }

        @Override
        public Object executeTask() {

            System.out.println("start executing-------" + com.objs);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
            }
            System.out.println("executing task...........");
            return "key event";
        }

        @Override
        public void resultTask(Object objs) {
            System.out.println("-------result ##########----" + objs);

        }
    }
    
    /**

* builder pattern to ange the way the  table columns are created and edited  
* 
* 
     
     * 
     * 
     * 
     */ 
     
    /**......
     * Creates new form InvoiceUI
     */
    public InvoiceUI() {
        initComponents();
        init();
//        cButton1.addActionListener(commandFind);
        tinv.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
//               commandFind.execute();
            }
        
        });
       
    }
    
    public void init(){
        items=new ItemService().getDao().getAll();
        tblInvoiceLine1.setModelClass(SalesInvoiceLineItem.class);
        tblInvoiceLine1.setPropertiesEL(new String[]{"item","qty","price","lineAmount"});       
        tblInvoiceLine1.setColumnHeader(new String[]{"Item","QTY","Amount","Line Totel"});        
        DoubleCellEditor dce=new DoubleCellEditor();                
        DoubleCellEditor dceA=new DoubleCellEditor();                
        ObjectCellEditor<Item> itce=new ObjectCellEditor(tblInvoiceLine1);
        
        itce.initPopup(new String[]{"id","code"}, new String[]{"ID","Code"},"id");
        itce.setPopListner(new PopupListner() {
            @Override
            public List searchItem(Object searchQry) {
            return items ;
            }
        });                
        
        tblInvoiceLine1.setCellEditors(itce,dce,dceA);    
        
        tblInvoiceLine1.modelToTable(new ArrayList());
       
        controlPanel1.setCrudController(this);
        tblInvoiceLine1.addModelToTable(new SalesInvoiceLineItem()); 
        
        tblInvoiceLine1.setTableInteractionListner( new TableInteractionListner(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void onCellEditing(Object cel,int col) {
             //get bus object
             //get table object
             //get editing row object 
             //get editing property
             //validate things   
             System.out.println(" Row obj "+cel);            
             //set value for column property
             //get table object collection
             //get GUI object
             /**
             use the builder pattern to  use the table  to 
             * column is created with the option to create the column 
             * 
             * column option are 
             */
             SalesInvoice si=getBusObject();
             ((SalesInvoiceLineItem)cel).calculateLineItem();
             tblInvoiceLine1.replaceModel(cel);             
            }       
        });
        tinv.addKeyListener(new MyKey());
    }

    @Override
    public void save() {
        System.out.println("+++++++++perisitence begin++++++++++++");
        
        super.save();
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoiceLine1 = new org.components.controls.ModelEditableTable();
        controlPanel1 = new com.components.custom.ControlPanel();
        cButton1 = new org.components.controls.CButton();
        tinv = new org.components.controls.CTextField();
        cButton2 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton5 = new org.components.controls.CButton();
        cButton6 = new org.components.controls.CButton();
        cButton7 = new org.components.controls.CButton();
        cButton8 = new org.components.controls.CButton();
        cButton9 = new org.components.controls.CButton();

        jScrollPane2.setViewportView(tblInvoiceLine1);

        cButton1.setText("Find");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        cButton2.setText("Add");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });

        cButton3.setText("Remove");
        cButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton3ActionPerformed(evt);
            }
        });

        cButton4.setText("Copy");
        cButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton4ActionPerformed(evt);
            }
        });

        cButton5.setText("Up");
        cButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton5ActionPerformed(evt);
            }
        });

        cButton6.setText("Down");
        cButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton6ActionPerformed(evt);
            }
        });

        cButton7.setText("Clear");
        cButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton7ActionPerformed(evt);
            }
        });

        cButton8.setText("Clear Line");
        cButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton8ActionPerformed(evt);
            }
        });

        cButton9.setText("Top");
        cButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(controlPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)))
                .addComponent(cButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(tinv, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tinv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        System.out.println("finding sinister value...........");
        //command pettern
        //long exausting task
        // get business objects
        //pass uis
        //update uis
        
//        invoker(commandFind);
        System.out.println("finding sinister value...........");        
    }//GEN-LAST:event_cButton1ActionPerformed

    
    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        tblInvoiceLine1.addModelToTable(new SalesInvoiceLineItem());        
    }//GEN-LAST:event_cButton2ActionPerformed

    private void cButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton3ActionPerformed
        tblInvoiceLine1.removeSelectedRow();        
    }//GEN-LAST:event_cButton3ActionPerformed

    private void cButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton7ActionPerformed
        tblInvoiceLine1.clear();
    }//GEN-LAST:event_cButton7ActionPerformed

    private void cButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton4ActionPerformed
        tblInvoiceLine1.copySelectedElement();
    }//GEN-LAST:event_cButton4ActionPerformed

    private void cButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton5ActionPerformed
        tblInvoiceLine1.moveSelectedLineUp();
        
    }//GEN-LAST:event_cButton5ActionPerformed

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        tblInvoiceLine1.moveSelectedLineDown();
    }//GEN-LAST:event_cButton6ActionPerformed

    private void cButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton8ActionPerformed

    }//GEN-LAST:event_cButton8ActionPerformed

    private void cButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton9ActionPerformed

    }//GEN-LAST:event_cButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cButton5;
    private org.components.controls.CButton cButton6;
    private org.components.controls.CButton cButton7;
    private org.components.controls.CButton cButton8;
    private org.components.controls.CButton cButton9;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.ModelEditableTable tblInvoiceLine1;
    private org.components.controls.CTextField tinv;
    // End of variables declaration//GEN-END:variables

    public SalesInvoice getBusObject() {
        
        SalesInvoice si= new SalesInvoice();
        si.setInvNo(tinv.getText());
        List<SalesInvoiceLineItem> salesInvoiceLineItems= tblInvoiceLine1.getModelCollection();
        si.setLineItems(salesInvoiceLineItems);
        return busObject;
    }


}

class CommandExe extends SwingWorker<Object, Object>{

    public ICommand command;
   
    public CommandExe() {
    }
    
    
    @Override
    protected Object doInBackground() throws Exception {
        return command.executeTask();
    }
    
    @Override
    protected void done() {
        Object obj=null;
        try {
            obj=get();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Getting result ");
        command.resultTask(obj);
    }

}

interface ICommand  {

    public Object executeTask();
    
    public void resultTask(Object  objs);
}

class Command {

    private ICommand command;
    public ArrayList objs;
    public ArrayList result;

    public Command(ICommand command) {
        this.command = command;
        objs = new ArrayList();
        result = new ArrayList();
    }

    public void invoke() {
       CommandExe com= new CommandExe();
       com.command = command;
       
       com.execute();
    }

    public void setParam(Object object) {
        objs.add(object);
    }

    public void setView(Object object) {
        result.add(object);
    }

}
