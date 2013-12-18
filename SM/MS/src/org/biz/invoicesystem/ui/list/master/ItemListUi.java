package org.biz.invoicesystem.ui.list.master;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import org.biz.app.ui.util.PagedListUI;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.dao.master.ItemDAO;
import org.biz.invoicesystem.entity.master.Item;
import app.utils.SystemStatic;
import org.biz.invoicesystem.master.ui.ItemMasterTab;
import org.biz.invoicesystem.master.ui.ItemMasterUI2;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.windows.TabPanelUI;

public class ItemListUi extends TabPanelUI implements PagedListUI {

    private ItemService itemService;
    private ItemMasterTab mastertab;
    private ItemMasterUI2 formUi;
    int currentPageNo = 0;
    List<Item> items;

    public ItemListUi() {
//        
//    init();
    }

    @Override
    public void init() {
        initComponents();
        itemService = new ItemService();
        items = new ArrayList<Item>();
        callVeryFirstPage();
        cPaginatedPanel1.setService(itemService);
        cPaginatedPanel1.setQryName(ItemDAO.findItemListByCode);
        cPaginatedPanel1.setListUI(this);

    }

    public void callVeryFirstPage() {

        try {
            currentPageNo = 0;
            loadItemList2Tbl(currentPageNo, getDynamicQuery());
//            cPageCount.setText("" + currentPageNo + " OF " + pageCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int pageCount() {
        int x = 0;
        try {
            x = itemService.getDao().getListSize() % SystemStatic.GRID_LIST_SIZE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public String getDynamicQuery() {
        String qq = "";

        if (UIEty.tcToStr(cItmDescription) == null || UIEty.tcToStr(cItmDescription).trim().equals("")) {

            qq = "SELECT e FROM Item e  order by e.id asc";
        } else {
            qq = "SELECT e FROM Item e Where e.description LIKE '%" + UIEty.tcToStr(cItmDescription) + "%'  order by e.id asc";
        }


        return qq;
    }

    public void loadItemList2Tbl(final int currentPageNo, final String dynamicQuery) {

        SwingWorker<List<Item>, Object> sw = new SwingWorker<List<Item>, Object>() {

            @Override
            protected List<Item> doInBackground() throws Exception {
                //     List<Item> items=getItemService().getDao().selectAll();

                List<Item> items = getItemService().getDao().getPaginatedData(currentPageNo, dynamicQuery);
                return items;
            }

            @Override
            protected void done() {
                if (loadTable()) {
                    return;
                }
            }
        };

        sw.execute();


    }

    private boolean loadTable() {
        try {
            TableUtil.cleardata(tblItemList);

            for (Item i : items) {
                TableUtil.addrowSR(tblItemList, new Object[]{i.getCode(), i.getDescription(), i.getCategory(), i.getUnit1SalesPrice(), i.getWholesalePrice()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void callFormUi() {
        try {

//            getMastertab().getItemTabPane().setSelectedIndex(getMastertab().getItemTabPane().indexOfTab(ItemMasterTab.FormUiTabName));
            getMastertab().getItemTabPane().setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////
    //when user double click on item row in table...bring him to form view the particular item... 

    public void callItemByCode(String itemCode) {

        try {
            Item item = itemService.getDao().findItemByCode(itemCode);
            formUi.clear();
            formUi.setSelectedItem(item);
            formUi.etyToUI(item);
            callFormUi();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cItmDescription = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemList = new org.components.controls.CxTable();
        cButton5 = new org.components.controls.CButton();
        cPaginatedPanel1 = new org.biz.app.ui.util.CPaginatedPanel();

        setLayout(null);

        jLabel1.setText("Item Description Search");
        add(jLabel1);
        jLabel1.setBounds(20, 20, 130, 20);

        cItmDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cItmDescriptionActionPerformed(evt);
            }
        });
        cItmDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cItmDescriptionKeyTyped(evt);
            }
        });
        add(cItmDescription);
        cItmDescription.setBounds(150, 20, 470, 25);

        tblItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description", "Category", "Unit Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tblItemList.setColumnSelectionAllowed(true);
        tblItemList.setFont(new java.awt.Font("Tahoma", 0, 14));
        tblItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemListMouseClicked(evt);
            }
        });
        tblItemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblItemListKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemList);
        tblItemList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblItemList.getColumnModel().getColumn(0).setMinWidth(85);
        tblItemList.getColumnModel().getColumn(0).setPreferredWidth(85);
        tblItemList.getColumnModel().getColumn(0).setMaxWidth(85);
        tblItemList.getColumnModel().getColumn(1).setMinWidth(200);
        tblItemList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblItemList.getColumnModel().getColumn(1).setMaxWidth(200);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 810, 370);

        cButton5.setText("Find");
        cButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton5ActionPerformed(evt);
            }
        });
        add(cButton5);
        cButton5.setBounds(630, 20, 53, 23);
        add(cPaginatedPanel1);
        cPaginatedPanel1.setBounds(180, 60, 390, 40);
    }// </editor-fold>//GEN-END:initComponents
//  try {
//            loadItemList2Tbl(0, getDynamicQuery());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }//try {delete
//            String itemid = (String) tblItemList.getValueAt(tblItemList.getSelectedRow(), 0);
//            if (itemid == null || itemid.equals("")) {
//                MessageBoxes.wrnmsg(null, "Please Select an Item.", "Empty Item");
//                return;
//            }
//            Item exist = itemService.getDao().findItemByCode(itemid);
//            if (exist == null || exist.getCode().equals("")) {
//                MessageBoxes.wrnmsg(null, "No Item Found.", "Empty Item");
//                return;
//            }
//            String[] ObjButtons = {"Yes", "No"};
//            int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);
//
//
//            if (PromptResult == 0) {
//                itemService.getDao().delete(exist);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            MessageBoxes.errormsg(null, e.getMessage(), "Error");
//        }  try {clsoe
////      String itemid=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
////          if(itemid==null || itemid.equals("")){
////           MessageBoxes.wrnmsg(null,"Please Select an Item.","Empty Item");                 
////                return;
////            }  
//        } catch (Exception e) {
//            e.printStackTrace();
//            MessageBoxes.errormsg(null, e.getMessage(), "Error");
//        }
    private void cItmDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cItmDescriptionActionPerformed
        String qry = cItmDescription.getText();

        try {
            items = itemService.getDao().byCode(qry);
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cItmDescriptionActionPerformed

    private void tblItemListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemListKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            String itemCode = (String) tblItemList.getValueAt(tblItemList.getSelectedRow(), 0);
            callItemByCode(itemCode);

        }
    }//GEN-LAST:event_tblItemListKeyTyped
//try {
//            if (tblItemList.getRowCount() == 0) {
//                return;
//            }
//            String itemid = (String) tblItemList.getValueAt(tblItemList.getSelectedRow(), 0);
//            if (itemid == null || itemid.equals("")) {
//                MessageBoxes.wrnmsg(null, "Please Select an Item.", "Empty Item");
//                return;
//            }
//            Item exist = itemService.getDao().findItemByCode(itemid);
//            if (exist == null || exist.getCode().equals("")) {
//                MessageBoxes.wrnmsg(null, "No Item Found.", "Empty Item");
//                return;
//            }
//            String[] ObjButtons = {"Yes", "No"};
//            int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to Copy this Item ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);
//
//
//            if (PromptResult == 0) {
//                exist.setId(EntityService.getEntityService().getKey(""));
//                String oldCode = exist.getCode();
//                exist.setCode("Copy " + oldCode);
//
//
//                itemService.getDao().save(exist);
//                getFormUi().entity2Ui(exist);
//                callFormUi();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            MessageBoxes.errormsg(null, e.getMessage(), "Error");
//        }
//     TableUtil.cleardata(tblItemList);
//        currentPageNo = 0;
//        loadItemList2Tbl(currentPageNo, getDynamicQuery());
//        cPageCount.setText("" + currentPageNo + " OF " + pageCount());
//    
//    TableUtil.cleardata(tblItemList);
//        currentPageNo -= 1;
//
//        if (currentPageNo < 0) {
//            currentPageNo = 0;
//
//        }
//
//        loadItemList2Tbl(currentPageNo, getDynamicQuery());
//        cPageCount.setText("" + currentPageNo + " OF " + pageCount());
    
//    if (true) {//pagination applicable
//            int nextpage = currentPageNo + 1;
//
//            itemService.moveToNextPage(ItemService.qryName, currentPageNo);
//
//            //rel;oad list 
//            // reload pagenation cxomponent
//
//        }
//    TableUtil.cleardata(tblItemList);
//        currentPageNo = pageCount();
//        loadItemList2Tbl(currentPageNo, getDynamicQuery());
//        cPageCount.setText("" + currentPageNo + " OF " + pageCount());
    private void cItmDescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cItmDescriptionKeyTyped
        //loadItemList2Tbl(currentPageNo,getDynamicQuery());
        //get query string with filters 
        //---if queryed goto page or next page
        //execute query 
        //return list - dont return whole no point return only first page and totel count
        //list should be paged 
        //pagination buttons should be ready 
    }//GEN-LAST:event_cItmDescriptionKeyTyped

    private void tblItemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemListMouseClicked
        try {
            if (evt.getClickCount() == 2) {

                String itemCode = (String) tblItemList.getValueAt(tblItemList.getSelectedRow(), 0);
                callItemByCode(itemCode);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblItemListMouseClicked

    private void cButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton5ActionPerformed


        String qry = cItmDescription.getText();
        items = itemService.getDao().findItemListByCode(qry);
        loadTable();


    }//GEN-LAST:event_cButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton5;
    private org.components.controls.CTextField cItmDescription;
    private org.biz.app.ui.util.CPaginatedPanel cPaginatedPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tblItemList;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Customer List";
    }

    @Override
    public JPanel getJPanel() {

        return this;
    }

    /**
     * @return the itemService
     */
    public ItemService getItemService() {
        return itemService;
    }

    /**
     * @param itemService the itemService to set
     */
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * @return the mastertab
     */
    public ItemMasterTab getMastertab() {
        return mastertab;
    }

    /**
     * @param mastertab the mastertab to set
     */
    public void setMastertab(ItemMasterTab mastertab) {
        this.mastertab = mastertab;
    }

    /**
     * @return the formUi
     */
    public ItemMasterUI2 getFormUi() {
        return formUi;
    }

    /**
     * @param formUi the formUi to set
     */
    public void setFormUi(ItemMasterUI2 formUi) {
        this.formUi = formUi;
    }

    @Override
    public void loadDataWithList(List list) {
        this.items = list;
        loadTable();
    }
}
