/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * popdialog.java
 *
 * Created on 15-Apr-2011, 16:19:23
 */
package org.biz.invoicesystem.master.ui;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.biz.app.ui.util.TableUtil;
import org.components.controls.CxTable;

/**
 *
 * @author mjawath
 */
public abstract class Popdialog extends javax.swing.JDialog {

    JTextField textField;
    JComboBox comboField;
    Component component;
    ComboBoxEditor  comboEditor;
    int column=0;
    int selectedColumn=0;
    JTable secTable;


    public Popdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    public Popdialog() {
        super();
        setUndecorated(true);
//        initComponents();
    }
    public Popdialog(JTextField field,List list) {
        super();
        setUndecorated(true);
        component = field;
        textField=field;        
//        initComponents();
    }
    public List items;
    private Object selectedItem;

    public void searchItem(String text, int... indice) {
        try {
            // May throw PatternSyntacError if values like [, ( + etc... typed
            ((TableRowSorter) table.getRowSorter()).setRowFilter(RowFilter.regexFilter("(?i).*" + text + ".*"));

        } catch (PatternSyntaxException e) {
            System.out.println("Pattern syntax error");
        }
    }

    public void init(JTextField field,List list) {

        component = field;
        textField=field;
        items=list;
        setFocusableWindowState(false);
        table.setFocusable(false);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        populateTable();

        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && Popdialog.this.isVisible()) {
                    int x = table.getSelectedRow();
                    if (x > -1) {
                        selectItem();
                        action();
                        return;
                    }
                }if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && e.isControlDown()) {
//                  int x=getSecTable().getSelectedRow();
//                    System.out.println("dsfgmnsdfgsdkfgnsdngs");
//                    TableUtil.selectNextRow(getSecTable(), e);
//                    Object val = TableUtil.getSelectedValue(getSecTable(), 0);
//                    if (val != null) {
//                        textField.setText(val.toString());
//                        textField.setCaretPosition(textField.getText().length());
//                        textField.selectAll();
//                    }
//                  selectTableRow();
                    if (secTable!=null) {
                  TableUtil.selectNextRow(secTable, e);
                }
                  
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
             
                    moveTableSelection(e);
                   
                    return;
                }
                
                if (e.getKeyCode() == KeyEvent.VK_DELETE && e.isControlDown()) {
                    System.out.println(e);
                    deleteAction();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {                    
                  closePopup();
                    return;
                }
                  if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && e.isControlDown()) {
                  closePopup();
                  return;
                  }
                if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
                 closePopup();
                   
                 return;
                 
                }
//                if (e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED) {
                    searchItem(textField.getText(), column);
                int rc = table.getRowCount();
                int sr = table.getSelectedRow();
                if (sr == -1 && rc > 0) {
                    table.getSelectionModel().setSelectionInterval(0, 0);
                }
//                 if (!isVisible()) {
                    showPopup();
//                }
//                }                              
            }
        });
        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                closePopup();
            }
        });

    }
    
    public void init(JComboBox jcb) {

        comboField=jcb;
        comboEditor=jcb.getEditor();
        component = comboEditor.getEditorComponent();
        setFocusableWindowState(false);
        table.setFocusable(false);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        if(items!=null && !items.isEmpty()){
        populateTable();
        }
        

       comboEditor.getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && Popdialog.this.isVisible()) {
                    int x = table.getSelectedRow();
                    if (x > -1) {
                        selectItem();
                        action();
                        return;
                    }
                }if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && e.isControlDown()) {
//                  int x=getSecTable().getSelectedRow();
//                    System.out.println("dsfgmnsdfgsdkfgnsdngs");
//                    TableUtil.selectNextRow(getSecTable(), e);
//                    Object val = TableUtil.getSelectedValue(getSecTable(), 0);
//                    if (val != null) {
//                        textField.setText(val.toString());
//                        textField.setCaretPosition(textField.getText().length());
//                        textField.selectAll();
//                    }
//                  selectTableRow();
                    if (secTable!=null) {
                  TableUtil.selectNextRow(secTable, e);
                }
                  
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
             
                    moveTableSelection(e);
                   
                    return;
                }
                
                if (e.getKeyCode() == KeyEvent.VK_DELETE && e.isControlDown()) {
                    System.out.println(e);
                    deleteAction();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {                    
                  closePopup();
                    return;
                }
                  if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && e.isControlDown()) {
                  closePopup();
                  return;
                  }
                if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
                 closePopup();
                   
                 return;
                 
                }
//                if (e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED) {
                    searchItem(comboField.getEditor().getItem().toString(), column);
                int rc = table.getRowCount();
                int sr = table.getSelectedRow();
                if (sr == -1 && rc > 0) {
                    table.getSelectionModel().setSelectionInterval(0, 0);
                }
//                 if (!isVisible()) {
                    showPopup();
//                }
//                }
               
                
            }
        });
        comboEditor.getEditorComponent().addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                closePopup();
            }
        });

    }

    public void moveTableSelection(KeyEvent e) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(table, e);
    }

    public void showPopup() {
        if (!this.isVisible()) {            
            this.setLocation((int)component.getLocationOnScreen().getX()+20,(int)component.getLocationOnScreen().getY()+component.getHeight());
            this.setVisible(true);
        }
    }

    public void closePopup() {
        if (this.isVisible()) {
            this.setVisible(false);
        }
    }

    public void setSecTable(JTable secTable) {
        this.secTable = secTable;
    }

    public JTable getSecTable() {
        return secTable;
    }
    public void deleteAction(){}
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new org.components.controls.CxTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 0));

        table.setBackground(new java.awt.Color(255, 204, 204));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public CxTable getCxTable1() {
        return table;
    }

    public void selectItem() {
        Object ob = TableUtil.getSelectedModelsValueAt(table, selectedColumn);
        if(component instanceof JComboBox){
        comboField.setSelectedItem(ob.toString());
        }
        if(component instanceof JTextField){
        textField.setText(ob.toString());
        }
        
        setItem();
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }


    public void selectTableRow(){

    }

    public void action() {
    }

    public String selectedText() {
         if(component instanceof JComboBox){
        return  comboField.getSelectedItem().toString();
        }
        if(component instanceof JTextField){
        return textField.getText();
        }       
        return "";
    }

    public Object getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setTable(CxTable table) {
        this.table = table;
    }

    public void populateTable() {
        

    }

    public void populateTable(List items) {
        this.items = items;
        populateTable();
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }


    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JTable getTable() {
        return table;
    }

    protected abstract void setItem();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable table;
    // End of variables declaration//GEN-END:variables
}
