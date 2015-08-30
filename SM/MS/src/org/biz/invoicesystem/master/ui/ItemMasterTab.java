package org.biz.invoicesystem.master.ui;

import java.util.List;
import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.windows.TabPanelUI;

public class ItemMasterTab extends TabPanelUI {

    public static String ListUiTabName = "List";
    public static String FormUiTabName = "Form";
    List<Item> items;
    ItemService itemService;
    //   EntityService es;
    //   ItemPopUp ipu;
//   Item selectedItem;

    public ItemMasterTab() {
        initComponents();
        init();



    }

    ////////////////////////
    @Override
    public void init() {
        try {
            itemService = new ItemService();
            itemListUi1.init();
            itemListUi1.setMastertab(this);
            itemMasterUI21.setMastertab(this);
            itemListUi1.setFormUi(itemMasterUI21);
            itemMasterUI21.setListUi(itemListUi1);
            itemMasterUI21.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
///////////////////////////////////////

//    public void callItemByCode(String itemid){
//    
//        try {
//     itemListUi1.callItemByCode(itemid);            
//     
//        } catch (Exception e) {
//      e.printStackTrace();  }
//    
//    }
//    
    //////////////////////////////////////////////////////
    @Override
    public void updateEntityUI() {

        getItemTabPane().setSelectedIndex(1);//getItemTabPane().indexOfTab("List")
        itemListUi1.loadItemList2Tbl(0, itemListUi1.getDynamicQuery());
    }

    ///////////////////////     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tItemDescription = new org.components.controls.CTextField();
        itemTabPane = new javax.swing.JTabbedPane();
        itemMasterUI21 = new org.biz.invoicesystem.master.ui.ItemMasterUIxxxxx();
        itemListUi1 = new org.biz.invoicesystem.ui.list.master.ItemListUix();

        itemTabPane.addTab("tab2", itemMasterUI21);
        itemTabPane.addTab("tab1", itemListUi1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(itemTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.biz.invoicesystem.ui.list.master.ItemListUix itemListUi1;
    private org.biz.invoicesystem.master.ui.ItemMasterUIxxxxx itemMasterUI21;
    private javax.swing.JTabbedPane itemTabPane;
    private org.components.controls.CTextField tItemDescription;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Item Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }

    /**
     * @return the itemTabPane
     */
    public javax.swing.JTabbedPane getItemTabPane() {
        return itemTabPane;
    }

    /**
     * @param itemTabPane the itemTabPane to set
     */
    public void setItemTabPane(javax.swing.JTabbedPane itemTabPane) {
        this.itemTabPane = itemTabPane;
    }
}
