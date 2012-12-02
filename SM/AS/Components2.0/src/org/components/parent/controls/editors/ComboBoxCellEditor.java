/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nnjj
 */
public class ComboBoxCellEditor extends CellEditor {

    private JComboBox component;
    private JTable tbl;
    // This method is called when a cell value is edited by the user.

    public ComboBoxCellEditor(JTable tbl) {
        this.tbl = tbl;
        init();
    }
    

    

    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public boolean isValide() {
        return true;
    }
//
//    public ComboBoxCellEditor(JTable tbl) {
//        init();
////        super(new JComboBox());
////        component = (JComboBox) getComponent();
////        component.setEditable(true);
//        //  component.getEditor().addActionListener(new AbstractAction() {
//        
//        this.tbl = tbl;
////        init();
//
//    }

    public boolean isCellValid() {
        return true;
    }

    private void init() {
        //specify
        component = new JComboBox();
//        component.setEditable(true);
//       ComponentFactory.createDouble(component);
//        component.getEditor().addActionListener(new AbstractAction() {
//
//            public void actionPerformed(ActionEvent e) {
//                stopCellEditing();
//            }
//        });
        component.setEditable(true);
        component.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     if (isCellValid()) {
                    stopCellEditing();
                }
                }
            }
        });
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {              
               if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isCellValid()) {
                    stopCellEditing();
                }
                   
              } 
            }
        });
//        component.addActionListener(new AbstractAction() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("sdfsdf");
//                    if (isCellValid()) {
//                    stopCellEditing();
//                }
//           
//            }
//        });

    }

   

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        ((JTextComponent)((JComboBox) component).getEditor().getEditorComponent()).setText("" + value);

        System.out.println("/**********  "+value);
        ((JTextComponent)((JComboBox) component).getEditor().getEditorComponent()).setText("" + value);
        ((JTextComponent)((JComboBox) component).getEditor().getEditorComponent()).selectAll();
        
        return component;
    }

    @Override
    public Object getCellEditorValue() {
        return component.getEditor().getItem();
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    @Override
    public void getEditingValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
