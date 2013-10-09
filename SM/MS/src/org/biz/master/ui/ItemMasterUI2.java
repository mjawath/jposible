package org.biz.master.ui;

import com.components.custom.PopupListner;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import org.biz.app.ui.util.Command;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.dao.service.GenericDAOUtil;
import org.biz.dao.service.Service;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.dao.master.SupplierDAO;
import org.biz.invoicesystem.entity.master.Category;
import org.biz.invoicesystem.entity.master.ExtraSalesPrice;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.ItemBarcode;
import org.biz.invoicesystem.entity.master.ItemVariation;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.master.ui.*;
import org.biz.invoicesystem.service.master.CategoryService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.ui.list.master.ItemListUi;
import org.components.parent.controls.editors.TableInteractionListner;
import org.components.windows.DetailPanel;

public class ItemMasterUI2 extends DetailPanel<Item> {

    List<Item> items;
    List<Category> categorys;
    ItemService itemService;
    CategoryService categoryService;//f
    ItemPopUp ipu;
    private ItemMasterTab mastertab;
    private ItemListUi listUi;
    private String copiedItemId;  //this is not item code...keep in mind purpose of updating copied item
    private JFileChooser chooser;
    List<File> images = new ArrayList<File>();  
    TableInteractionListner tblInterUnit;
    

    public ItemMasterUI2() {
//        initComponents();//pp
//        keyListeners();
        super();
    }

    /////////////////////////////////////
    public Object[] loadComboData() {
        //c.category , c.unitOne , c.unitTwo c.location      
        List<Object[]> lst = itemService.getDao().loadComboItems();

        Set<String> catz = new TreeSet<String>();
        Set<String> un1z = new TreeSet<String>();
        Set<String> un2z = new TreeSet<String>();
        Set<String> locz = new TreeSet<String>();

        for (Object[] ss : lst) {
            String category = (String) ss[0];
            catz.add(category);
            String un1 = (String) ss[1];
            un1z.add(un1);
            String un2 = (String) ss[2];
            un2z.add(un2);
            String location = (String) ss[3];
            locz.add(location);

        }

        return new Object[]{catz, un1z, un2z, locz};
    }

    
    
    public void init() {
     
                initComponents();
            super.init();
               
    
        try {
//            itemService =(ItemService)service;
            selectedObject = new Item();
//            categoryService =new CategoryService();
//            getClass().getFields()[0].equals(ui);
//            items = itemService.getDao().getAll();
//            categorys = categoryService.getDao().getAll();

            crudcontrolPanel.setCrudController(this);
            ///init filechooser and set filter
            ///////////////////////
            chooser = new JFileChooser(new File("."));
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {

                    if (f.isDirectory()) {
                        return true;
                    }
                    String s = f.getName();
                    int i = s.lastIndexOf('.');

                    if (i > 0 && i < s.length() - 1) {
                        if (s.substring(i + 1).toLowerCase().equals("jpg") || s.substring(i + 1).toLowerCase().equals("png") || s.substring(i + 1).toLowerCase().equals("gif") || s.substring(i + 1).toLowerCase().equals("png")) {
                            return true;
                        }
                    }

                    return false;
                }

                @Override
                public String getDescription() {
                    return "Images Only";
                }
            });
            chooser.setCurrentDirectory(null);
            UOM.setUOMType(tunittype);

//            tunittype.setModel(new DefaultComboBoxModel(new String[]{"3","4"}));
            events();
            ////////////////////////////////////////
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        crudcontrolPanel.set
        tblunitprices.setPropertiesEL(new String[]{"simbol", "salesPrice","type", "multi"});
        tblunitprices.setColumnHeader(new String[]{"Simbol", "SalesPrice","Type" ,"Multi"});


        tItemCategory.setObjectToTable(categorys);        
        tItemCategory.setPropertiesEL(new String[]{"id", "code", "description"});
        tItemCategory.setTitle(new String[]{"id", "Code", "Descrption"});
        tItemCategory.setSelectedProperty("code");
//        tItemCategory.getPagedPopUpPanel().setCommand(command);
        tItemCategory.getPagedPopUpPanel().setPoplistener(new PopupListner() {
            
            @Override
            public List searchItem(Object searchQry) {
                
                return itemService.categoryServise().getDao().getAll();
            }

            @Override
            public Object[] getTableData(Object obj) {
                Category cat=(Category)obj;
                return new Object[]{cat,cat.getId(),cat.getCode()};
            }
        });



        cPanel6.addToFocus(tunitsymbot);
        cPanel6.addToFocus(tunitprice);
        cPanel6.addToFocus(tunittype);
        cPanel6.addToFocus(tContainsQty);
        tblInterUnit=new TableInteractionListner(){

            @Override
            public Object[] getTableData(Object row) {
                UOM sil=(UOM)row;
                return new Object[]{sil,sil.getSimbol(),sil.getSalesPrice(),sil.getType(),sil.getMulti()
                };
            }
        
        };
        tblunitprices.setTableInteractionListner(tblInterUnit);
        
    }

    ////////////////////////////
    public void events() {




        try {
            //item code listener
            tItemcode.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        try {
                            Item item = itemService.getDao().findItemByCode(UIEty.tcToStr(tItemcode));
                            if (item != null) {
                                etyToUI(item);

                            }
                            tItemDescription.requestFocus();

                        }
                        catch (Exception exx) {
                            exx.printStackTrace();
                            tItemcode.requestFocus();
                        }

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
            });//finished item code listener



            ////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////


            tContainsQty.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    try {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                            /**
                             *
                             * get the line validate object add to table adjust
                             * selection
                             */
                            //get selected row id
                            //find selected row in the uoms
                            // add new or replace current one
                            //update table
                            UOM uom = new UOM();
                            UOM object = (UOM) TableUtil.getSelectedTableObject(tblunitprices);
                            if (object != null) {
                                uom = object;
                            }
                            uom.setSimbol(tunitsymbot.getText());

                            //check this with id and symbol if same skip
                            //if diff do not accept

                            int ty = tunittype.getSelectedIndex();
                            uom.setType((byte) ty);

                            uom.setSalesPrice(tunitprice.getDoubleValue());
                            uom.setMulti(tContainsQty.getDoubleValue0());
                            Item item = getBusObject();
                            //prime  unit
                            //logic changes type is defined 
                            if (item.checkUOMExist(uom)) {
                                MessageBoxes.wrnmsg(ItemMasterUI2.this, "unit already exists ", "duplicate uom");
                                return;
                            }
                            
                            
                            item.addUOMorUpdate(uom);//should change to keep list of uoms
                            //we can skip current primary uom setting becas we r using only one primary key
                            // we cannnot give only primary key
                            // 
                            //set it when we save data ..
                            if (object == null) {
                                tblunitprices.addModelToTable(uom);
                            }
                            else {
                                tblunitprices.replaceModel(uom);
                            }
                            cleatUOM();
                            tunitsymbot.requestFocus();

                        }
                    }
                    catch (Exception exx) {
                        exx.printStackTrace();
                    }
                }
            });

            tblBarcode.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        // TableUtil.cleardata(tblVariation);
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            String type = UIEty.tcToStr(titemmark);
                            String barcode = UIEty.tcToStr(tItemBarcode);
                            ItemBarcode itemBarcode = new ItemBarcode(type, barcode);
                            //validate 
                            selectedObject.addItemBarCode(itemBarcode);

                            addToBarcode();
                        }
                    }
                    catch (Exception eee) {
                        eee.printStackTrace();
                    }


                }
            });


            tunittype.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("---   ---");
                    //apply primary type when select only one item

                    // allow only one carton , and whole sale
                    //other entries are other type and are allowed multiple time
                    // when deleting a entry chek this contitions
                    //when primary is deleted user should be notified
                    // symbol | price | type | mutiply* primary unit |


                }
            });


            tblunitprices.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (equals(e.getValueIsAdjusting())) {
                        return;
                    }
                    //get selected uom from list
                    UOM uom = TableUtil.getSelectedTableObject(tblunitprices);
                    if(uom==null)return;//set uom to UI
                    UIEty.objToUi(tunitprice, uom.getSalesPrice());
                    UIEty.objToUi(tContainsQty, uom.getMulti());
                    UIEty.objToUi(tunitsymbot, uom.getSimbol());
                    
                }
            });

            tadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {   
                    addUnitToTable();
                }
            });
            
            addToFocus(tItemcode);
            addToFocus(tItemDescription);
            addToFocus(tSupplierItem);
            addToFocus(ttype);
            addToFocus(tmodel);
            addToFocus(tItemCategory);
            addToFocus(tItemCostPrice);
            addToFocus(tItemCostPrice);
            

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteUnitRow() {
        //should check for primary keys deletion !!!!!

//        Object ob = TableUtil.getSelectedValue(tblunitprices, 0);
//        if (ob != null) {
//            for (Iterator<UOM> it = selectedObject.getUoms().iterator(); it.hasNext();) {
//                if (ob.equals(it.next().getId())) {
//                    it.remove();
//                    addUnitToTable(selectedObject);
//                    break;
//                }
//            }
//            //update uom table
//        }
        //
        tblunitprices.removeSelectedRow();
        cleatUOM();
    }

    
    private void addUnitToTable(){
        //get selected uom from list
        UOM uom = TableUtil.getSelectedTableObject(tblunitprices);
        if (uom == null) {
            return;
        }
        //set uom to UI
        UIEty.objToUi(tunitprice, uom.getSalesPrice());
        UIEty.objToUi(tContainsQty, uom.getMulti());
        UIEty.objToUi(tunitsymbot, uom.getSimbol());
        //set combo 

    }
    
    private void addUnitToTable(Item item) {
//            String u = um.getGuom() != null ? um.getGuom().getSimbol() : null;
//            TableUtil.addrow(tblunitprices, new Object[]{um.getId(), um.getType(), um.getSimbol(), um.getSalesPrice(),
//                        um.getMulti(), u});
        tblunitprices.modelToTable(item.getUoms());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - barcode -
    //  this method ADD barcode TO THE TABLE OF barcodeTbl...TABLE.
    public void addToBarcode() {

        TableUtil.cleardata(tblunitprices);
        for (UOM um : selectedObject.getUoms()) {
            String u = um.getGuom() != null ? um.getGuom().getSimbol() : null;
            TableUtil.addrow(tblunitprices, new Object[]{um.getId(), um.getType(), um.getSimbol(), um.getSalesPrice(),
                                                         um.getMulti(), u});
        }
        TableUtil.addnewrow(tblunitprices);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tItemTrakSerial = new org.components.controls.CCheckBox();
        tItemCostPrice = new org.components.controls.CTextField();
        tItemMinimumStock = new org.components.controls.CTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tItemCommissionValue = new org.components.controls.CTextField();
        tSupplierItem = new org.components.controls.CComboBox();
        tItemDescription = new org.components.controls.CTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cButton1 = new org.components.controls.CButton();
        tItemdiscount = new org.components.controls.CTextField();
        jLabel2 = new javax.swing.JLabel();
        tItemLandingCost = new org.components.controls.CTextField();
        cScrollPane1 = new org.components.controls.CScrollPane();
        cPanel4 = new org.components.containers.CPanel();
        cLabel7 = new org.components.controls.CLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        cScrollPane2 = new org.components.controls.CScrollPane();
        cPanel6 = new org.components.containers.CPanel();
        tunitprice = new org.components.controls.CTextField();
        tunitsymbot = new org.components.controls.CTextField();
        tContainsQty = new org.components.controls.CTextField();
        cButton2 = new org.components.controls.CButton();
        cLabel2 = new org.components.controls.CLabel();
        tunittype = new org.components.controls.CComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblunitprices = new org.components.controls.ModelEditableTable();
        tadd = new org.components.controls.CButton();
        cLabel10 = new org.components.controls.CLabel();
        cLabel11 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBarcode = new javax.swing.JTable();
        titemmark = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        tItemBarcode = new org.components.controls.CTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPriceRanges = new javax.swing.JTable();
        tRngeValue = new org.components.controls.CTextField();
        tPriceRange = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        tWholesalePrice = new org.components.controls.CTextField();
        tmodel = new org.components.controls.CTextField();
        cPanel3 = new org.components.containers.CPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tMetaInfo = new org.components.controls.CTextArea();
        cLabel3 = new org.components.controls.CLabel();
        jLabel13 = new javax.swing.JLabel();
        tItemMinimumPrice = new org.components.controls.CTextField();
        tItemLocation = new org.components.controls.CComboBox();
        tItemdiscValue = new org.components.controls.CTextField();
        tItemCommission = new org.components.controls.CTextField();
        jLabel4 = new javax.swing.JLabel();
        tItemReOrder = new org.components.controls.CTextField();
        jLabel14 = new javax.swing.JLabel();
        cPanel2 = new org.components.containers.CPanel();
        tItemTrakExpiry = new org.components.controls.CCheckBox();
        tItemTrakNonStockItem = new org.components.controls.CCheckBox();
        tItemTrakInactive = new org.components.controls.CCheckBox();
        tItemTrakManfctringItem = new org.components.controls.CCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tItemcode = new org.components.controls.CTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        crudcontrolPanel = new com.components.custom.ControlPanel();
        cLabel8 = new org.components.controls.CLabel();
        ttype = new org.components.controls.CTextField();
        cLabel9 = new org.components.controls.CLabel();
        ttype1 = new org.components.controls.CTextField();
        tItemCategory = new com.components.custom.TextFieldWithPopUP<Category>();

        tItemTrakSerial.setText("Track Serial Number");
        tItemTrakSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tItemTrakSerial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakSerial.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tItemTrakSerial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        add(tItemCostPrice);
        tItemCostPrice.setBounds(80, 300, 90, 25);
        add(tItemMinimumStock);
        tItemMinimumStock.setBounds(80, 450, 210, 25);

        jLabel9.setText("Min.Price");
        add(jLabel9);
        jLabel9.setBounds(20, 330, 60, 20);

        jLabel22.setText("Cost Price");
        add(jLabel22);
        jLabel22.setBounds(20, 300, 60, 20);
        add(tItemCommissionValue);
        tItemCommissionValue.setBounds(200, 390, 90, 25);
        add(tSupplierItem);
        tSupplierItem.setBounds(80, 110, 210, 30);
        add(tItemDescription);
        tItemDescription.setBounds(80, 50, 210, 25);

        jLabel17.setText("Min.Stock");
        add(jLabel17);
        jLabel17.setBounds(20, 450, 60, 20);

        jLabel10.setText("%");
        add(jLabel10);
        jLabel10.setBounds(60, 360, 20, 20);

        jLabel23.setText("Val");
        add(jLabel23);
        jLabel23.setBounds(180, 380, 20, 40);

        jLabel16.setText("Commission");
        add(jLabel16);
        jLabel16.setBounds(20, 390, 60, 20);

        cButton1.setText("Browse");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(300, 390, 80, 20);
        add(tItemdiscount);
        tItemdiscount.setBounds(80, 360, 90, 25);

        jLabel2.setText("Description ");
        add(jLabel2);
        jLabel2.setBounds(20, 50, 60, 20);
        add(tItemLandingCost);
        tItemLandingCost.setBounds(200, 300, 90, 25);

        cScrollPane1.setAutoscrolls(true);
        cScrollPane1.setViewportView(cPanel4);

        add(cScrollPane1);
        cScrollPane1.setBounds(300, 300, 550, 90);

        cLabel7.setText("You Can Select More than one Product Image");
        add(cLabel7);
        cLabel7.setBounds(390, 390, 370, 25);

        jLabel15.setText("Location");
        add(jLabel15);
        jLabel15.setBounds(20, 420, 60, 20);

        jLabel21.setText("Supplier");
        add(jLabel21);
        jLabel21.setBounds(20, 120, 50, 20);

        cPanel6.setMinimumSize(new java.awt.Dimension(150, 150));
        cPanel6.setPreferredSize(new java.awt.Dimension(600, 400));
        cPanel6.setLayout(null);

        tunitprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tunitpriceActionPerformed(evt);
            }
        });
        cPanel6.add(tunitprice);
        tunitprice.setBounds(130, 20, 90, 25);
        cPanel6.add(tunitsymbot);
        tunitsymbot.setBounds(20, 20, 100, 25);
        cPanel6.add(tContainsQty);
        tContainsQty.setBounds(340, 20, 100, 25);

        cButton2.setText("remove");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });
        cPanel6.add(cButton2);
        cButton2.setBounds(440, 50, 70, 19);

        cLabel2.setText("Symbol");
        cPanel6.add(cLabel2);
        cLabel2.setBounds(30, 0, 60, 20);

        tunittype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tunittypeActionPerformed(evt);
            }
        });
        cPanel6.add(tunittype);
        tunittype.setBounds(230, 20, 80, 23);

        tblunitprices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "obj", "Symbol", "Unit", "Type", "Price"
            }
        ));
        tblunitprices.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblunitprices);
        tblunitprices.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        cPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(0, 50, 440, 120);

        tadd.setText("Add");
        tadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taddActionPerformed(evt);
            }
        });
        cPanel6.add(tadd);
        tadd.setBounds(440, 20, 70, 19);

        cLabel10.setText("Multiply * primUnit");
        cPanel6.add(cLabel10);
        cLabel10.setBounds(320, 0, 130, 20);

        cLabel11.setText("Type");
        cPanel6.add(cLabel11);
        cLabel11.setBounds(230, 0, 70, 20);

        cLabel12.setText("Price");
        cPanel6.add(cLabel12);
        cLabel12.setBounds(130, 0, 60, 20);

        cScrollPane2.setViewportView(cPanel6);

        jTabbedPane1.addTab("Unit", cScrollPane2);

        jPanel4.setLayout(null);

        tblBarcode.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Barcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblBarcode);

        jPanel4.add(jScrollPane5);
        jScrollPane5.setBounds(50, 70, 340, 110);
        jPanel4.add(titemmark);
        titemmark.setBounds(50, 30, 160, 25);

        cLabel4.setText("Barcode Number");
        jPanel4.add(cLabel4);
        cLabel4.setBounds(230, 5, 130, 20);

        cLabel5.setText("Item Mark");
        jPanel4.add(cLabel5);
        cLabel5.setBounds(50, 5, 100, 20);

        cLabel6.setText("Enter");
        jPanel4.add(cLabel6);
        cLabel6.setBounds(404, 30, 40, 25);
        jPanel4.add(tItemBarcode);
        tItemBarcode.setBounds(230, 30, 160, 25);

        jTabbedPane1.addTab("Barcode", jPanel4);

        jPanel1.setLayout(null);

        jLabel7.setText("Whole Sale  Price");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 10, 90, 20);

        tblPriceRanges.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Range", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPriceRanges);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 110, 340, 70);

        tRngeValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tRngeValueKeyTyped(evt);
            }
        });
        jPanel1.add(tRngeValue);
        tRngeValue.setBounds(270, 70, 120, 25);

        tPriceRange.setEditable(true);
        jPanel1.add(tPriceRange);
        tPriceRange.setBounds(50, 70, 130, 23);

        cLabel1.setText("Feed Price Ranges For Wholesale Needs");
        jPanel1.add(cLabel1);
        cLabel1.setBounds(60, 40, 310, 25);
        jPanel1.add(tWholesalePrice);
        tWholesalePrice.setBounds(260, 10, 180, 25);
        jPanel1.add(tmodel);
        tmodel.setBounds(110, 10, 140, 25);

        jTabbedPane1.addTab("Price Range", jPanel1);

        cPanel3.setLayout(null);

        tMetaInfo.setColumns(20);
        tMetaInfo.setRows(5);
        jScrollPane4.setViewportView(tMetaInfo);

        cPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(10, 80, 440, 96);

        cLabel3.setText("Meta Information ");
        cPanel3.add(cLabel3);
        cLabel3.setBounds(10, 50, 300, 25);

        jTabbedPane1.addTab("Meta Details ", cPanel3);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(310, 20, 540, 220);

        jLabel13.setText("$");
        add(jLabel13);
        jLabel13.setBounds(190, 300, 10, 20);
        add(tItemMinimumPrice);
        tItemMinimumPrice.setBounds(80, 330, 90, 25);

        tItemLocation.setEditable(true);
        add(tItemLocation);
        tItemLocation.setBounds(80, 420, 210, 23);
        add(tItemdiscValue);
        tItemdiscValue.setBounds(200, 360, 90, 25);
        add(tItemCommission);
        tItemCommission.setBounds(80, 390, 90, 20);

        jLabel4.setText("Category");
        add(jLabel4);
        jLabel4.setBounds(10, 240, 60, 14);
        add(tItemReOrder);
        tItemReOrder.setBounds(80, 480, 210, 25);

        jLabel14.setText("Val");
        add(jLabel14);
        jLabel14.setBounds(180, 360, 20, 30);

        cPanel2.setLayout(null);

        tItemTrakExpiry.setText("Track Expiry ");
        tItemTrakExpiry.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakExpiry.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakExpiry);
        tItemTrakExpiry.setBounds(40, 0, 70, 40);

        tItemTrakNonStockItem.setText("Non Stock Item");
        tItemTrakNonStockItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakNonStockItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakNonStockItem);
        tItemTrakNonStockItem.setBounds(120, 0, 90, 40);

        tItemTrakInactive.setText("Inactive");
        tItemTrakInactive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakInactive.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakInactive);
        tItemTrakInactive.setBounds(220, 0, 60, 40);

        tItemTrakManfctringItem.setText("Manufacturing Item");
        tItemTrakManfctringItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakManfctringItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakManfctringItem);
        tItemTrakManfctringItem.setBounds(300, 0, 120, 40);

        add(cPanel2);
        cPanel2.setBounds(310, 240, 450, 40);

        jLabel19.setText("Re Order");
        add(jLabel19);
        jLabel19.setBounds(20, 480, 60, 20);

        jLabel12.setText("Discount ");
        add(jLabel12);
        jLabel12.setBounds(20, 360, 60, 20);
        add(tItemcode);
        tItemcode.setBounds(80, 20, 210, 25);

        jLabel6.setText("Landing Cost");
        add(jLabel6);
        jLabel6.setBounds(200, 280, 80, 20);

        jLabel1.setText("Item Code");
        add(jLabel1);
        jLabel1.setBounds(20, 20, 50, 20);
        add(crudcontrolPanel);
        crudcontrolPanel.setBounds(420, 440, 340, 30);

        cLabel8.setText("Type");
        add(cLabel8);
        cLabel8.setBounds(20, 150, 40, 20);
        add(ttype);
        ttype.setBounds(80, 150, 210, 30);

        cLabel9.setText("Model");
        add(cLabel9);
        cLabel9.setBounds(10, 190, 50, 20);
        add(ttype1);
        ttype1.setBounds(80, 190, 210, 30);
        add(tItemCategory);
        tItemCategory.setBounds(80, 230, 210, 25);
    }// </editor-fold>//GEN-END:initComponents
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void cleatUOM() {
        tunitprice.clear();
        tunitsymbot.clear();
        tContainsQty.clear();
        tblunitprices.clearSelection();
    }

    public Item uiToEty(Item i) throws Exception {
        try {
            i.setId(EntityService.getEntityService().getKey(""));
            i.setCode(UIEty.tcToStr(tItemcode));
            i.setDescription(UIEty.tcToStr(tItemDescription));
//            i.setCategory(UIEty.cmbtostr(tItemCategory)); //    combo
            i.setSupplierId(UIEty.cmbtostr(tSupplierItem)); //    combo
            i.setCost(UIEty.tcToDble0(tItemCostPrice));//tItemCostPrice
            i.setLandCost(UIEty.tcToDble0(tItemLandingCost)); //tItemLandingCost
            i.setMinSalesPrice(UIEty.tcToDble0(tItemMinimumPrice)); //tItemMinimumPrice
            i.setDiscount(UIEty.tcToDble0(tItemdiscount));//tItemdiscount
            i.setDiscountValue(UIEty.tcToDble0(tItemdiscValue));
            i.setCommission(UIEty.tcToDble0(tItemCommission));//tItemCommission
            i.setCommissionValue(UIEty.tcToDble0(tItemCommissionValue));//tItemCommission
            i.setLocation(UIEty.cmbtostr(tItemLocation));//tItemLocation   combo
            i.setMinStock(UIEty.tcToDble0(tItemMinimumStock));//tItemMinimumStock
            i.setReOrder(UIEty.tcToDble0(tItemReOrder)); //tItemReOrder
            i.setTrackSerial(tItemTrakSerial.isSelected());  //tItemTrakSerial chk
            i.setTrackExpiry(tItemTrakExpiry.isSelected());  //tItemTrakExpiry chk
            i.setNonStockItems(tItemTrakNonStockItem.isSelected());//tItemTrakNonStockItem chk
            i.setManufactItem(tItemTrakManfctringItem.isSelected());//tItemTrakManfctringItem chk      
            i.setInactive(tItemTrakInactive.isSelected());//tItemTrakInactive chk      
            i.setWholesalePrice(UIEty.tcToDble0(tWholesalePrice));//tWholesalePrice
            i.setMetaInfo(tMetaInfo.getText());  //tMetaInfo
            i.setExtrasalespriceCollection(ui2ExtraSalesPrice(tblPriceRanges, i.getId()));
            i.setModel(UIEty.tcToStr(tmodel));
            i.setType(UIEty.tcToStr(ttype));

            i.setCategory(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return i;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void callListTab() {
        try {
            getMastertab().getItemTabPane().setSelectedIndex(getMastertab().getItemTabPane().indexOfTab(ItemMasterTab.ListUiTabName));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ItemVariation> ui2ItemVariation(JTable tbl, String itemid) {
        List<ItemVariation> lstOfVariation = new ArrayList<ItemVariation>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {

                Vector row = it.next();
                ItemVariation var = new ItemVariation();
                var.setId(EntityService.getEntityService().getKey(""));
                var.setDescription(row.get(0) == null ? "" : row.get(0).toString());
                var.setsPrice1(row.get(1) == null ? 0.0 : Double.parseDouble(row.get(1).toString()));
                var.setsPrice2(row.get(2) == null ? 0.0 : Double.parseDouble(row.get(2).toString()));
                lstOfVariation.add(var);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstOfVariation;
    }

    public List<ExtraSalesPrice> ui2ExtraSalesPrice(JTable tbl, String itemid) {
        List<ExtraSalesPrice> lstOfExSalePrice = new ArrayList<ExtraSalesPrice>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {
                Vector row = it.next();
                ExtraSalesPrice extraSprice = new ExtraSalesPrice();
                extraSprice.setId(EntityService.getEntityService().getKey(""));
                extraSprice.setDescription(row.get(0) == null ? "" : row.get(0).toString());
                extraSprice.setPrice(row.get(1) == null ? 0.0 : Double.parseDouble(row.get(1).toString()));
                lstOfExSalePrice.add(extraSprice);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstOfExSalePrice;
    }

    ////////////////////////////
    public List<ItemBarcode> ui2Barcodes(JTable tbl, String itemid) {
        List<ItemBarcode> lstFBarcodes = new ArrayList<ItemBarcode>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {
                Vector row = it.next();
                ItemBarcode bCode = new ItemBarcode();
                bCode.setId(EntityService.getEntityService().getKey(""));
                bCode.setType(row.get(0) == null ? "" : row.get(0).toString());
                bCode.setBarcode(row.get(1) == null ? "" : row.get(1).toString());
                lstFBarcodes.add(bCode);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstFBarcodes;
    }

    public List<UOM> tableToUoms(JTable tbl) {
        List<UOM> uoms = new ArrayList<UOM>();
        return uoms;

    }

    public void itemVariation2Ui(List<ItemVariation> lstOfVariation) {

        try {

            if (lstOfVariation == null) {
                return;
            }
            for (Iterator<ItemVariation> it = lstOfVariation.iterator(); it.hasNext();) {
                ItemVariation i = it.next();


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void extraSalesPrice2Ui(List<ExtraSalesPrice> lstOfExtraPrice) {


        try {

            if (lstOfExtraPrice == null) {
                return;
            }
            for (Iterator<ExtraSalesPrice> it = lstOfExtraPrice.iterator(); it.hasNext();) {
                ExtraSalesPrice i = it.next();


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void barcode2Ui(List<ItemBarcode> lstOfBarcode) {


        try {
            if (lstOfBarcode == null) {
                return;
            }

            for (Iterator<ItemBarcode> it = lstOfBarcode.iterator(); it.hasNext();) {
                ItemBarcode i = it.next();


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //***************persistence logic*************************//
    public void preSave() {
//        busObject = getBusObject();
//        toSave.add(busObject);
    }

    @Override
    public void postCreate(Object deleObj) {

//        itemService.getDao().save(selectedObject);
        saveImages(busObject.getCode(), images);
    }

    @Override
    public void postUpdate(Object deleObj) {

        //        selectedItem.setId(get.getId());
//        Date mDate = GenericDAOUtil.currentTime();
//        selectedObject.setEditedDate(mDate);
//        itemService.getDao().update(selectedObject);

        //put to thread 
        deleteImages(busObject.getCode());
        saveImages(busObject.getCode(), images);

    }

    @Override
    public boolean isValideEntity() {
        if (UIEty.isTxtFieldNullOrEmpty(tItemcode)) {
            MessageBoxes.wrnmsg(null, "Please Type Item Code", "Empty Item Code");
            tItemcode.requestFocus();
            return false;
        }
        return super.isValideEntity();
    }

    public void delete() {

        if (UIEty.tcToStr(tItemcode) == null || UIEty.tcToStr(tItemcode).equals("")) {
            MessageBoxes.wrnmsg(null, "Please Type Item Code", "Empty Item Code");
            return;
        }
        super.delete();
    }

    public void clear() {
        tPriceRange.setSelectedItem("");
        tRngeValue.setText("");
        TableUtil.cleardata(tblPriceRanges);
        TableUtil.cleardata(tblBarcode);
        tblunitprices.clear();

        titemmark.setText("");
        tItemBarcode.setText("");
        setBusObject(new Item());

        cPanel4.removeAll();
        cPanel4.revalidate();

        images.clear();
        tItemcode.requestFocus();
    }

    @Override
    public Item getBusObject() {
        Item item = new Item();
//        item.setId(EntityService.getEntityService().getKey(""));
        item.setCode(UIEty.tcToStr(tItemcode));
        item.setDescription(UIEty.tcToStr(tItemDescription));
        item.setCategory(tItemCategory.getSelectedObject()); //    combo
        item.setSupplierId(UIEty.cmbtostr(tSupplierItem)); //    combo
        item.setCost(UIEty.tcToDouble(tItemCostPrice));//tItemCostPrice
        item.setLandCost(UIEty.tcToDouble(tItemLandingCost)); //tItemLandingCost
        item.setMinSalesPrice(UIEty.tcToDouble(tItemMinimumPrice)); //tItemMinimumPrice
        item.setDiscount(UIEty.tcToDouble(tItemdiscount));//tItemdiscount
        item.setDiscountValue(UIEty.tcToDouble(tItemdiscValue));
        item.setCommission(UIEty.tcToDouble(tItemCommission));//tItemCommission
        item.setCommissionValue(UIEty.tcToDouble(tItemCommissionValue));//tItemCommission
        item.setLocation(UIEty.cmbtostr(tItemLocation));//tItemLocation   combo
        item.setMinStock(UIEty.tcToDouble(tItemMinimumStock));//tItemMinimumStock
        item.setReOrder(UIEty.tcToDouble(tItemReOrder)); //tItemReOrder
        item.setTrackSerial(tItemTrakSerial.isSelected());  //tItemTrakSerial chk
        item.setTrackExpiry(tItemTrakExpiry.isSelected());  //tItemTrakExpiry chk
        item.setNonStockItems(tItemTrakNonStockItem.isSelected());//tItemTrakNonStockItem chk
        item.setManufactItem(tItemTrakManfctringItem.isSelected());//tItemTrakManfctringItem chk      
        item.setInactive(tItemTrakInactive.isSelected());//tItemTrakInactive chk      
        item.setWholesalePrice(UIEty.tcToDouble(tWholesalePrice));//tWholesalePrice
        item.setMetaInfo(tMetaInfo.getText());  //tMetaInfo
        item.setExtrasalespriceCollection(ui2ExtraSalesPrice(tblPriceRanges, item.getId()));
        item.setModel(UIEty.tcToStr(tmodel));
        item.setType(UIEty.tcToStr(ttype));

        return item;
    }

    public void setBusObject(Item obj) {
        UIEty.objToUi(tItemcode, obj.getCode());
        UIEty.objToUi(tItemDescription, obj.getDescription());
        tItemCategory.setSelectedObject(obj.getCategory());
        UIEty.objToUi(tSupplierItem, obj.getSupplierId());
        UIEty.objToUi(tItemCostPrice, obj.getCost());
        UIEty.objToUi(tItemLandingCost, obj.getLandCost());
        UIEty.objToUi(tItemMinimumPrice, obj.getMinSalesPrice());
        UIEty.objToUi(tItemdiscount, obj.getDiscount());
        UIEty.objToUi(tItemdiscValue, obj.getDiscountValue());
        UIEty.objToUi(tItemCommission, obj.getCommission());
        UIEty.objToUi(tItemCommissionValue, obj.getCommission());
        UIEty.objToUi(tItemLocation, obj.getLocation());
        UIEty.objToUi(tItemMinimumStock, obj.getMinStock());
        UIEty.objToUi(tItemReOrder, obj.getReOrder());
        UIEty.objToUi(tItemTrakSerial, obj.getTrackSerial());
        UIEty.objToUi(tItemTrakExpiry, obj.getTrackExpiry());
        UIEty.objToUi(tItemTrakNonStockItem, obj.getNonStockItems());
        UIEty.objToUi(tItemTrakManfctringItem, obj.getManufactItem());
        UIEty.objToUi(tItemTrakInactive, obj.getInactive());
        UIEty.objToUi(tWholesalePrice, obj.getWholesalePrice());
        UIEty.objToUi(tMetaInfo, obj.getMetaInfo());
        itemVariation2Ui(obj.getVariations());
        extraSalesPrice2Ui(obj.getExtrasalespriceCollection());
        tItemCategory.setSelectedObject(obj.getCategory());
        addUnitToTable(obj);
        loadImagesToPanel(obj.getCode());
    }

    @Override
    public void setService(Service service) {
        super.setService(service);
        itemService = (ItemService) service;
        
//        commandGUI.invoke();
    }

    public void loadGUIData() {

        loadComboData();
        //start gui data loader task
        //load gui data on edt       
    }
    
    Command commandGUI = new Command() {
        @Override
        public Object executeTask() {
            return loadComboData();
        }

        @Override
        public void resultTask(Object objs) {
            Object[] obs = (Object[]) objs;
            //set values to combos
        }
    };

    public void saveImages(String itemid, List<File> images) {
        try {
            int x = 1;
            for (File img : images) {
                System.out.println("img is " + img.getAbsolutePath());
                new File(SystemStatic.ITEM_IMAGE_PATH).mkdirs();

                String newImagePath = SystemStatic.ITEM_IMAGE_PATH + x + "-" + itemid + "-" + img.getName().substring(img.getName().lastIndexOf("."), img.getName().length());
                x++;

                File imgout1 = new File(newImagePath);

                imgout1.mkdirs();
                boolean d = ImageIO.write(ImageIO.read(img), getExtension(img), imgout1);

            }
            // this.images.clear();            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    //////////////////////////////////////

    public void deleteImages(String itemid) {
        try {
            File f = new File(SystemStatic.ITEM_IMAGE_PATH);
            File[] ff = f.listFiles();

            if (ff != null) {
                for (File file : ff) {
                    boolean b = Pattern.compile(Pattern.quote("-" + itemid + "-"), Pattern.CASE_INSENSITIVE).matcher(file.getName()).find();
                    if (b) {
                        file.delete();

                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    ///////////////////////////////////

    //////////////////////////////////////
    public void loadImagesToPanel(String itemid) {

        try {
            File f = new File(SystemStatic.ITEM_IMAGE_PATH);
            File[] ff = f.listFiles();
            List<File> itemImages = new ArrayList<File>();

            if (ff != null) {
                for (final File image : ff) {
                    boolean b = Pattern.compile(Pattern.quote("-" + itemid + "-"), Pattern.CASE_INSENSITIVE).matcher(image.getName()).find();
                    if (b) {
                        //load images to the panel 
                        final JLabel jl = new JLabel();

                        jl.addMouseListener(new MouseAdapter() {
                            JPopupMenu p = null;

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                p = viewLargeImg(jl, image);
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (p != null) {
                                    p.setVisible(false);
                                }
                            }
                        });

                        cPanel4.add(imagesloadresize(image.getAbsolutePath(), jl));

                        cPanel4.add(imagesloadresize(image.getAbsolutePath(), jl));
                        cPanel4.revalidate();


                    }

                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

///////////////////////////////////////////////////////////////////////
    public boolean pasteItem(Item i, String itemid) throws Exception {
        boolean b = false;
        try {
            i.setId(itemid);
            itemService.getDao().update(i);
            b = true;
        }
        catch (Exception e) {
            e.printStackTrace();

            b = false;
            throw e;
        }
        return b;
    }

    ////////////////////////////////////////////
    public String getExtension(File f)
            throws Exception {
        String extension = f.getName();
        int i = extension.lastIndexOf('.');

        if ((i > 0) && (i < extension.length() - 1)) {
            extension = extension.substring(i + 1).toLowerCase();
        }
        return extension;
    }

    private void tRngeValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tRngeValueKeyTyped
    }//GEN-LAST:event_tRngeValueKeyTyped

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        try {
            //    loadImagesToPanel("1000");
            //  deleteImages("1000");                            
            //     jPanel4.removeAll();
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();
//      JPanel panel = new JPanel(new FlowLayout());
//        
//        panel.setLayout(new FlowLayout());
//   JPopupMenu p=new JPopupMenu("imagepanel");
            //  cScrollPane1.add(panel);     
            for (final File image : files) {

                images.add(image);
                System.out.println("addedd image");
//    ImagePanel ii=new ImagePanel(new ImageIcon(image.getCanonicalPath()).getImage(),1);
//ImagePanel ii2=new ImagePanel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg").getImage(),1);

                //panel.setPreferredSize(new Dimension(300, 200));
                //ii2.setVisible(true);

//      System.out.println("canonical o]path "+image.getCanonicalPath());
//      System.out.println("absolute o]path "+image.getAbsolutePath());
                //     panel.add(new JLabel(new ImageIcon(image.getAbsolutePath())));                             
                // panel.add(ii2);                             
                // panel.setPreferredSize(new Dimension(200, 200));  
                ////    panel.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));
                //ImagePanel ii=new ImagePanel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg").getImage(),1);
//   ii.setPreferredSize(new Dimension(200,150));
//   ii.setVisible(true);

                //jScrollPane3.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));                   
// jScrollPane3.add(panel);                   


                final JLabel jl = new JLabel();

                jl.addMouseListener(new MouseAdapter() {
                    JPopupMenu p = null;

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        p = viewLargeImg(jl, image);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (p != null) {
                            p.setVisible(false);
                        }
                    }
                });

                cPanel4.add(jl);


                cPanel4.add(imagesloadresize(image.getAbsolutePath(), jl));

            }
            cPanel4.revalidate();

            MessageBoxes.okmsg(null, "ok", "title");
//    panel.setVisible(true);  
//   p.add(panel);
//   p.show(this,10, 100);
//   p.setVisible(true);
//          
        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        deleteUnitRow();
    }//GEN-LAST:event_cButton2ActionPerformed

    private void tunitpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tunitpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tunitpriceActionPerformed

    private void tunittypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tunittypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tunittypeActionPerformed

    private void taddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taddActionPerformed

    public JPopupMenu viewLargeImg(JLabel lbl, File image) {
        //  System.out.println("viewlargeImg Methd image name is "+image.getAbsolutePath());
        JPopupMenu p = new JPopupMenu("imagepanel");
        try {
            JPanel panel = new JPanel(new FlowLayout());
            //      panel.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));           
            Icon i = lbl.getIcon();
            //    ImagePanel ii = new ImagePanel(iconToImage(i), 50);
            //    ImagePanel ii = new ImagePanel(image, 1);
            //   panel.add(imagesloadresize( , lbl));
            JLabel jj = new JLabel();
            jj.setIcon(new ImageIcon(iconToImage(i).getScaledInstance(250, 250, 0)));
            panel.add(jj);
            panel.setVisible(true);
            panel.revalidate();
            p.add(panel);

            p.show(this, Toolkit.getDefaultToolkit().getScreenSize().width / 2, 0);
            p.setVisible(true);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        }
        else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                                GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            //      BufferedImage image = gc.createCompatibleImage(w, h);
            BufferedImage image = gc.createCompatibleImage(500, 500);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    private JLabel imagesloadresize(String ss, JLabel jl) {
        ImageIcon i = new ImageIcon(ss);
        Image i1 = i.getImage().getScaledInstance(100, 100, 4);

        Icon i12 = new ImageIcon(i1);
        jl.setIcon(i12);
        return jl;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.containers.CPanel cPanel4;
    private org.components.containers.CPanel cPanel6;
    private org.components.controls.CScrollPane cScrollPane1;
    private org.components.controls.CScrollPane cScrollPane2;
    private com.components.custom.ControlPanel crudcontrolPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.components.controls.CTextField tContainsQty;
    private org.components.controls.CTextField tItemBarcode;
    private com.components.custom.TextFieldWithPopUP<Category> tItemCategory;
    private org.components.controls.CTextField tItemCommission;
    private org.components.controls.CTextField tItemCommissionValue;
    private org.components.controls.CTextField tItemCostPrice;
    private org.components.controls.CTextField tItemDescription;
    private org.components.controls.CTextField tItemLandingCost;
    private org.components.controls.CComboBox tItemLocation;
    private org.components.controls.CTextField tItemMinimumPrice;
    private org.components.controls.CTextField tItemMinimumStock;
    private org.components.controls.CTextField tItemReOrder;
    private org.components.controls.CCheckBox tItemTrakExpiry;
    private org.components.controls.CCheckBox tItemTrakInactive;
    private org.components.controls.CCheckBox tItemTrakManfctringItem;
    private org.components.controls.CCheckBox tItemTrakNonStockItem;
    private org.components.controls.CCheckBox tItemTrakSerial;
    private org.components.controls.CTextField tItemcode;
    private org.components.controls.CTextField tItemdiscValue;
    private org.components.controls.CTextField tItemdiscount;
    private org.components.controls.CTextArea tMetaInfo;
    private org.components.controls.CComboBox tPriceRange;
    private org.components.controls.CTextField tRngeValue;
    private org.components.controls.CComboBox tSupplierItem;
    private org.components.controls.CTextField tWholesalePrice;
    private org.components.controls.CButton tadd;
    private javax.swing.JTable tblBarcode;
    private javax.swing.JTable tblPriceRanges;
    private org.components.controls.ModelEditableTable tblunitprices;
    private org.components.controls.CTextField titemmark;
    private org.components.controls.CTextField tmodel;
    private org.components.controls.CTextField ttype;
    private org.components.controls.CTextField ttype1;
    private org.components.controls.CTextField tunitprice;
    private org.components.controls.CTextField tunitsymbot;
    private org.components.controls.CComboBox tunittype;
    // End of variables declaration//GEN-END:variables

    //////////////////////////////////////////////
    public Supplier getSupplier(String typedName) throws Exception {
        Supplier s = null;
        try {

            s = new SupplierDAO().findSupplierByCode(typedName);



        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return s;
    }
    //////////////////////////////////////////////////////

    /**
     * @return the selectedItem
     */
    public Item getSelectedItem() {
        return selectedObject;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(Item selectedItem) {
        this.selectedObject = selectedItem;
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
     * @return the listUi
     */
    public ItemListUi getListUi() {
        return listUi;
    }

    /**
     * @param listUi the listUi to set
     */
    public void setListUi(ItemListUi listUi) {
        this.listUi = listUi;
    }

    /**
     * @return the copiedItemId
     */
    public String getCopiedItemId() {
        return copiedItemId;
    }

    /**
     * @param copiedItemId the copiedItemId to set
     */
    public void setCopiedItemId(String copiedItemId) {
        this.copiedItemId = copiedItemId;
    }

    private void setuoms() {
        List<UOM> uoms = selectedObject.getUoms();

//        if(){}

        UOM pu = null;//comply with the preference  ...
//                            pu.setSimbol(tprimunit.getText());
//                            uom.setGuom(pu);
//                            List<UOM> uoms=selectedItem.getUoms();
        if (uoms != null && !uoms.isEmpty()) {
            pu = uoms.get(0);
        }
//                            selectedItem.addUOMorUpdate(uom);

        for (int i = 0; i < uoms.size(); i++) {
            UOM uom = uoms.get(i);
            if (i == 0) {
                continue;
            }
            uom.setGuom(pu);

        }
    }
}
/*
 ***********************************************************
 * //UOM if one item is selected then modify it if nothing selected then create
 * new primary unit item must have a primary key so uom should have a flag to
 * say it is primary * or uom has gom --has a relationship?? this is way
 * complecated types should be difined as primary , cartons, wholesale ,,,and
 * others ..
 *
 ****************
 * item mark should be considered agains !!!!!
 *
 *
 *
 * //enhancements
 *
 * using the number for the data ca n simplyfy the dataentry
 * 
 *
 *
 */
