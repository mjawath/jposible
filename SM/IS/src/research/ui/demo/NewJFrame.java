/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on Mar 26, 2012, 9:39:47 PM
 */
package research.ui.demo;

/**
 *
 * @author NUZAIR
 */
public class NewJFrame extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public NewJFrame() {
//        initComponents();
        
            jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(255, 102, 204));
        jLayeredPane1.setOpaque(true);

        jPanel1.setLayout(null);

        jButton1.setText("jButton1");
        jPanel1.add(jButton1);
        jButton1.setBounds(95, 33, 73, 23);

        jPanel1.setBounds(10, 20, 340, 100);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.POPUP_LAYER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane1.setBounds(0, 60, 375, 200);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 40, 380, 300);

        pack();
        
  
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        cButton1 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(255, 102, 204));
        jLayeredPane1.setOpaque(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLayeredPane1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 70, 375, 200);

        jPanel1.setLayout(null);

        jButton1.setText("jButton1");
        jPanel1.add(jButton1);
        jButton1.setBounds(95, 33, 73, 28);

        jLayeredPane1.add(jPanel1);
        jPanel1.setBounds(100, 100, 340, 90);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(40, 180, 520, 220);

        jToolBar1.setRollover(true);

        cButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/go-first-view.png"))); // NOI18N
        cButton1.setText("");
        cButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cButton1);

        cButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/ui/demo/go-previous-view.png"))); // NOI18N
        cButton4.setText("");
        cButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cButton4);

        cButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/ui/demo/go-next-view.png"))); // NOI18N
        cButton3.setText("");
        cButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cButton3);

        cButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/research/ui/demo/go-last-view.png"))); // NOI18N
        cButton2.setText("");
        cButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cButton2);

        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(140, 20, 310, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
