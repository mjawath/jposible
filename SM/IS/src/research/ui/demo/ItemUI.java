/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemUI.java
 *
 * Created on Nov 3, 2011, 7:12:49 PM
 */
package research.ui.demo;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAOUtil;

/**
 *
 * @author user
 */
public class ItemUI extends javax.swing.JFrame {

    /** Creates new form ItemUI */
    public ItemUI() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dropDownWithButton1 = new com.components.custom.DropDownWithButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(dropDownWithButton1);
        dropDownWithButton1.setBounds(330, 180, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new ItemUI().setVisible(true);
//            }
//        });
//        for (int x = 0; x < 10; x++) {
//        ArrayList al = new ArrayList();    
//        for (int i = 0; i < 253; i++) {
//        Item it=  new Item();
//        it.setId(""+i);
//        it.setCode("code "+System.currentTimeMillis()+333+i);
//        it.setDescription("desc "+System.currentTimeMillis()+4444+i);
//        al.add(it);
//        }
//        GenericDAOUtil.saveList(al);             
//           System.out.println("finished   "+1);
//        }
        System.out.println("finished");
        
        ArrayList al= new ArrayList();
        for (int i = 0; i < 28; i++) {
         List ls=   GenericDAOUtil.pagedData("", "select i from Item i order by i.id asc ", i);
            System.out.println(" ----  "+ls.size()+" -- ");
            al.addAll(ls);
        }
        
            System.out.println("--------------------"+al.size());            
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.components.custom.DropDownWithButton dropDownWithButton1;
    // End of variables declaration//GEN-END:variables
}
