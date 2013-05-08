
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LineItemPanel.java
 *
 * Created on Feb 23, 2012, 10:21:43 PM
 */
package invoicingsystem;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.components.containers.CPanel;
import org.components.parent.controls.PxTable;

/**
 *
 * @author nnjj
 */
public abstract class LineItemPanel extends CPanel implements IContainer {

    protected Object salesline;
    protected PxTable jt;
    private JLayeredPane layeredPane;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public LineItemPanel(JFrame jf) {
//        super(jf, true);
        super();
        init();
    }

    public void setLayer(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        if (layeredPane != null) {

            layeredPane.add(getPanel(), new Integer(1000));
        }
    }

    /** Creates new form LineItemPanel */
    public LineItemPanel() {
        super();
        init();

    }

    private void init() {
        focus = new ArrayList<IComponent>();
        setSize(900, 100);
        setPreferredSize(new Dimension(900, 100));
        initComponents();



    }

    public JPanel getPanel() {
        return this;
    }

    private void movepanel() {


        int sr = jt.getSelectedRow();
//    int rowhieht=252 +30;
        int srh = jt.getRowHeight(sr);


        Point point = SwingUtilities.convertPoint(jt, jt.getCellRect(sr, 0, true).getLocation(), layeredPane);

        this.setBounds((int) (point.getX()), (int) point.getY(), jt.getWidth(), srh);
        this.revalidate();
        this.repaint();
        if (!this.isVisible()) {
            this.setVisible(true);
        }

    }

    /**
     * shows the panel of the row detail on the table , set the focus to first component
     * selects th rows model 
     */
    public void showLineItemPanel() {

        //prob : - event thrown two times becas of selection change and mouse event calls this mothod so duplicaton of calls
        movepanel();
        //request focus to first componennt
        Component com = (Component) focus.get(0);
        com.requestFocus();
        selectEty();///TODO : fuuuuu,,,, in here becas of the null pointer / null id set to table 

    }

    public void requestFocuseToFirst() {
    }
    private JScrollPane pane;

    public void setScrollpane(JScrollPane scrollPane) {
        pane = scrollPane;

        pane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e) {
                movepanel();
            }
        });
    }

    public void setTable(PxTable tbl) {
        this.jt = tbl;


        UIEty.setKeyAction(this, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                LineItemPanel.this.setVisible(false);
//                ((KeyEvent)e).consume();
            }
        }, KeyEvent.VK_ESCAPE);
        UIEty.setKeyAction(this, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                TableUtil.selectNextRow(jt, KeyEvent.VK_UP);
            }
        }, KeyEvent.VK_UP);

        UIEty.setKeyAction(this, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                TableUtil.selectNextRow(jt, KeyEvent.VK_DOWN);
            }
        }, KeyEvent.VK_DOWN);


        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {
                    showLineItemPanel();
                }
            }
        });
        jt.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
//                for (Component component : LineItemPanel.this.getComponents()) {
//                    if(component.isFocusOwner()){
//                        LineItemPanel.this.setVisible(false);
//                    }
//                }
            }
        });
//        addWindowFocusListener(new WindowAdapter() {
//
//            @Override
//            public void windowLostFocus(WindowEvent e) {
////                LineItemPanel.this.setVisible(false);
//            }
//        });
        jt.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                showLineItemPanel();
            }
        });
    }

    public void setComponent(JComponent jc, int x, int y, int width, int hieht) {
        jc.setBounds(x, y, width, hieht);
//        this.getContentPane().add(jc);
//        pack();
    }

    public JTextField getTextField() {
        return textField;
    }

    public void dispatchEventx(KeyEvent event) {
        textField.requestFocus();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(textField, event);

//        cTextField1.select(cTextField1.getText().length()-1, cTextField1.getText().length()-1);
//        cTextField1.setCaretPosition(cTextField1.getText().length()-1);
//        event.consume();
    }

    public void setTextField(JTextField cTextField1) {
        this.textField = cTextField1;
    }
    protected JTextField textField;
    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    // End of variables declaration

    public void selectEty() {
        salesline = jt.getSelectedObject(SalesInvoiceLineItem.class);
        clear();
        selectedEtyToPanel();
    }

    @Override
    public void gotoNextComponent(Component comp) {
        //get current focused compnentt
        Component jc = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        //find from compnent list
        int x = 0;
        for (IComponent com : focus) {
            if (com == jc) {
                if (x >= 0 && x < focus.size() - 1) {
                    IComponent yx = focus.get(x + 1);
                    ((Component) yx).requestFocus();
                    return;
                }
            }
            x++;
        }

    }

    public void addToFocus(IComponent com) {
        focus.add(com);
        com.setContainer(this);

    }
    private List<IComponent> focus;

    @Override
    public void callBackAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
    }

    private void selectedEtyToPanel() {
    }
}
